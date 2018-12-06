package sample;

import DAO.DAOManager;
import FxTable.TableRowDataModel;
import Manager.NyaaFeedParser;
import VO.FileData;
import VO.Nyaa_si_Feed;
import VO.Nyaa_si_FeedMessage;
import VO.SrcRss;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

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
    private Label src_text;
    @FXML
    private TextField filePath;

    public static String Sfile_path;
    public static String src_title;


    private ArrayList<Nyaa_si_FeedMessage> torrentList;
    private NyaaFeedParser parser;
    private Nyaa_si_Feed feed;

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
                Sfile_path = filePath.getText();
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    if (event.getClickCount() == 2) {
                        System.out.println("Double clicked");
                        TableRowDataModel tm = myTableView.getSelectionModel().getSelectedItem();
                        parser.clickSaveFile(tm.titleProperty().getValue(), tm.pageLinkProperty().getValue());
                    }
                }
            }
        });

        list_Fov.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                myTableView.setItems(FXCollections.observableArrayList());
                Sfile_path = filePath.getText();
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    if (event.getClickCount() == 1) {
                        System.out.println("Double clicked");
                        String alias = list_Fov.getSelectionModel().getSelectedItems().toString();
                        String alias2 = alias.substring(1, alias.length() - 1);
                        ArrayList<Nyaa_si_FeedMessage> feedMessages = DAOManager.selectListItem(alias2);
                        System.out.println(feedMessages.size() + "체크");


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
        list_Fov.getItems().add("One");
        list_Fov.getItems().add("Two");
        list_Fov.getItems().add("Three");

        filePath.setText("D:\\RssExam");
    }


    public void btnTest() {

//        myTableView.getItems().removeAll();
        myTableView.setItems(FXCollections.observableArrayList());

        btn_Src.setDisable(true);
        String srcTitle = text_Src.getText();
        torrentList = new ArrayList<>();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                src_title = "https://nyaa.si/?page=rss&q=" + srcTitle;

                parser = new NyaaFeedParser("https://nyaa.si/?page=rss&q=" + srcTitle); //  + "&c=0_0&f=0&p=2"
//                parser = new NyaaFeedParser("http://leopard-raws.org/rss.php" + srcTitle ); //
                feed = parser.readFeed();

                try {
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

    }

    //즐겨찾기 추가
    public void btnFvo() {
        TextInputDialog dialog = new TextInputDialog("title");
        dialog.setTitle("Input Favorites");
        dialog.setHeaderText("제목을 입력해주세요.");
        dialog.setContentText("List Name :");
        Optional<String> result = dialog.showAndWait();


        for (Nyaa_si_FeedMessage message : torrentList) {
            message.setUser_alias(result.get());
            message.setUser_downCheck("1");
            message.setUser_lastDownDate("1");
            DAOManager.insertRssTorrent(message);
        }
        list_Fov.getItems().add(result.get());

        SrcRss srcRss = new SrcRss();
        srcRss.setSrc_alias(result.get());
        srcRss.setSrc_rssAdr(src_title);
        DAOManager.insertSrcRss(srcRss);

        System.out.println("즐겨찾기 추가 완료");
    }

}
