package sample;

import DAO.DAOManager;
import FxTable.TableRowDataModel;
import Manager.NyaaFeedParser;
import VO.FileData;
import VO.Nyaa_si_Feed;
import VO.Nyaa_si_FeedMessage;
import VO.RssSrc;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    @FXML
    private TableView<TableRowDataModel> myTableView;
    @FXML
    private TableColumn<TableRowDataModel, String> title_Column;
    @FXML
    private TableColumn<TableRowDataModel, String> category_Column;
    @FXML
    private TableColumn<TableRowDataModel, String> pubDate_Column;
    @FXML
    private TableColumn<TableRowDataModel, String> seeder_Column;
    @FXML
    private TableColumn<TableRowDataModel, String> down_Column;
    @FXML
    private TableColumn<TableRowDataModel, String> size_Column;
    @FXML
    private TableColumn<TableRowDataModel, String> pageLink_Column;
    @FXML
    private Button btn_Src;
    @FXML
    private TextField text_Src;
    @FXML
    private Button btn_checkSave;
    @FXML
    private Button btn_allSave;
    @FXML
    private ListView list_Fov;
    @FXML
    private Button btn_Fov;
    @FXML
    private Button btn_Rss;
    @FXML
    private Label src_text;
    @FXML
    private TextField filePath;
    @FXML
    private ComboBox<String> rss_SiteList;

    public static String Sfile_path;
    public static String src_title;


    private ArrayList<Nyaa_si_FeedMessage> torrentList;
    private NyaaFeedParser parser;
    private Nyaa_si_Feed feed;

    private DAOManager daoManager = new DAOManager();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title_Column.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        category_Column.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        pubDate_Column.setCellValueFactory(cellData -> cellData.getValue().pubDateProperty());
        seeder_Column.setCellValueFactory(cellData -> cellData.getValue().seederProperty());
        down_Column.setCellValueFactory(cellData -> cellData.getValue().downProperty());
        size_Column.setCellValueFactory(cellData -> cellData.getValue().sizeProperty());
        pageLink_Column.setCellValueFactory(cellData -> cellData.getValue().pageLinkProperty());


        myTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        myTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                Sfile_path = filePath.getText() + "\\";

                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    if (event.getClickCount() == 2) {
                        System.out.println("Double clicked");
                        TableRowDataModel tm = myTableView.getSelectionModel().getSelectedItem();
                        parser.saveFileItemClick(tm.titleProperty().getValue(), tm.pageLinkProperty().getValue());
                    }
                }
            }
        });

        //리스트 목록 클릭 리스너
        list_Fov.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btn_Fov.setDisable(true);
                myTableView.setItems(FXCollections.observableArrayList());
                Sfile_path = filePath.getText();
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    if (event.getClickCount() == 1) {
                        String alias = list_Fov.getSelectionModel().getSelectedItems().toString();
                        String alias2 = alias.substring(1, alias.length() - 1);
                        ArrayList<Nyaa_si_FeedMessage> feedMessages = daoManager.selectListItem(alias2);


                        Runnable runnable = new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    for (Nyaa_si_FeedMessage message : feedMessages) {
                                        System.out.println(message.toString());

                                        TableRowDataModel mm = new TableRowDataModel(
                                                new SimpleStringProperty(message.getTitle())
                                                , new SimpleStringProperty(message.getNyaa_category())
                                                , new SimpleStringProperty(message.getPubDate())
                                                , new SimpleStringProperty(message.getNyaa_seeders())
                                                , new SimpleStringProperty(message.getNyaa_downloads())
                                                , new SimpleStringProperty(message.getNyaa_size())
                                                , new SimpleStringProperty(message.getLink())
                                        );
                                        myTableView.getItems().add(mm);
//                        src_text.setText(" 개의 파일이 검색되었습니다.");
                                    }

                                } catch (Exception e) {
                                    e.printStackTrace();
                                    System.out.println("검색된 파일이 없습니다.");
                                }
                            }
                        };
                        Thread srcThread = new Thread(runnable);
                        srcThread.start();
                    }
                }
            }
        });

        //리스트 저장 목록 로드
        ArrayList<RssSrc> srcRss = daoManager.selectSrcRss();
        if (srcRss != null) {
            for (RssSrc rss : srcRss) {
                list_Fov.getItems().add(rss.getSrc_alias());
            }
        }
        btn_Fov.setDisable(true);
        btn_Rss.setDisable(true);

        rss_SiteList.getItems().add("Nyaa.si");
        rss_SiteList.getItems().add("도쿄도서관");
        rss_SiteList.getItems().add("leopard");
        rss_SiteList.getItems().add("horriblesubs");
        rss_SiteList.getItems().add("Ohys-Raws");
        filePath.setText("D:\\RssExam");
    }


    public void btnTest() {
        btn_Fov.setDisable(false);
        myTableView.setItems(FXCollections.observableArrayList());

        btn_Src.setDisable(true);
        String srcTitle = text_Src.getText();

        torrentList = new ArrayList<>();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (rss_SiteList.getPromptText().equals("Nyaa.si")) {
                    src_title = "https://nyaa.si/?page=rss&q=" + srcTitle;
                } else {

                    switch (rss_SiteList.getValue()) {
                        case "Nyaa.si":
                            src_title = "https://nyaa.si/?page=rss&q=" + srcTitle;
                            break;
                        case "도쿄도서관":
                            src_title = "https://www.tokyotosho.info/rss.php?terms=" + srcTitle;
                            break;
                        case "leopard":
                            src_title = "http://leopard-raws.org/rss.php?search=" + srcTitle;
                            break;
                        case "horriblesubs":
                            src_title = "http://horriblesubs.info/rss.php?res=" + srcTitle;
                            break;
                        case "Ohys-Raws":
                            src_title = "https://torrents.ohys.net/download/rss.php?dir=new&q=" + srcTitle;
                            break;

                    }
                }


                parser = new NyaaFeedParser(src_title);

                feed = parser.readFeed();

                try {
                    if (feed == null) {
                        System.out.println("리턴");
                        btn_Src.setDisable(false);
                        return;
                    }

                    for (Nyaa_si_FeedMessage message : feed.getMessages()) {
                        parser.saveUrlList(new FileData(message.getTitle(), message.getLink()));
                        torrentList.add(message);
                        System.out.println(message);

                        TableRowDataModel mm = new TableRowDataModel(
                                new SimpleStringProperty(message.getTitle())
                                , new SimpleStringProperty(message.getNyaa_category())
                                , new SimpleStringProperty(message.getPubDate())
                                , new SimpleStringProperty(message.getNyaa_seeders())
                                , new SimpleStringProperty(message.getNyaa_downloads())
                                , new SimpleStringProperty(message.getNyaa_size())
                                , new SimpleStringProperty(message.getLink())
                        );
                        myTableView.getItems().add(mm);
//                        src_text.setText(" 개의 파일이 검색되었습니다.");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("검색된 파일이 없습니다.");
                } finally {
                    btn_Src.setDisable(false);
                }
            }
        };
        Thread srcThread = new Thread(runnable);
        srcThread.start();
    }


    public void check_Down() {
//        parser.saveFile(Strin);
    }

    public void all_Down() {
        System.out.println("전체다운실행");
        parser.saveFile();

    }

    public void btnRss() {
        Parent root;
        Popup popup = new Popup();
        Window window = btn_Rss.getScene().getWindow();
        popup.setWidth(800);
        popup.setHeight(495);

        try {
            if (popup.isShowing()) {
                popup.hide();
            } else {
                root = FXMLLoader.load(getClass().getResource("RssSrc.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                popup.getContent().addAll(root);

                popup.show(window);
            }

//            stage.setTitle("Rss 추가");
//            stage.setScene(new Scene(root, 800, 495));
//            stage.setResizable(false);
//            stage.setScene(scene);
//            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("새창 열기 실패");
        }

    }

    //즐겨찾기 추가
    public void btnFvo() {
        TextInputDialog dialog = new TextInputDialog("title");
        dialog.setTitle("Input Favorites");
        dialog.setHeaderText("제목을 입력해주세요.");
        dialog.setContentText("List Name :");
        Optional<String> result = dialog.showAndWait();
        String resultTitle = result.get();

        ArrayList<RssSrc> rssSrc = daoManager.selectSrcRss();
        for (RssSrc src : rssSrc) {
            if (src.getSrc_alias().equals(resultTitle.trim())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "이미 등록된 이름 입니다.");
                alert.show();
                System.out.println("이미 등록된 이름 입니다.");
                return;
            }
        }

        for (Nyaa_si_FeedMessage message : torrentList) {
            message.setUser_alias(resultTitle);
            daoManager.insertRssTorrent(message);
        }
        list_Fov.getItems().add(resultTitle);

        RssSrc srcRss = new RssSrc();
        srcRss.setSrc_alias(resultTitle);
        srcRss.setSrc_rssAdr(src_title);
        daoManager.insertSrcRss(srcRss);

        System.out.println("즐겨찾기 추가 완료");
    }

}
