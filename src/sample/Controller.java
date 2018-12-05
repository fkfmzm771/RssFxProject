package sample;

import DAO.DAOManager;
import FxTable.TableRowDataModel;
import Manager.NyaaFeedParser;
import VO.FileData;
import VO.Nyaa_si_Feed;
import VO.Nyaa_si_FeedMessage;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    @FXML
    private TableView<TableRowDataModel> myTableView;
    @FXML
    private TableColumn<TableRowDataModel, String> check_Column;
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

    private List<Nyaa_si_FeedMessage> torrentList;
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

//        myTableView.setItems(myList);
    }


    public void btnTest() {
        myTableView.getItems().removeAll();
        System.out.println(myTableView.getItems().size());
        btn_Src.setDisable(true);
        String srcTitle = text_Src.getText();
        torrentList = new ArrayList<>();

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                parser = new NyaaFeedParser("https://nyaa.si/?page=rss&q=" + srcTitle + "&c=0_0&f=0");
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
                    }
                } catch (Exception e) {
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
        parser.saveFile(6);
    }

    public void all_Down() {
        System.out.println("전체다운실행");
        parser.saveFile(-1);

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
//        result.ifPresent(name -> System.out.println("Your name: " + name));



        for (Nyaa_si_FeedMessage message : torrentList) {
            message.setUser_alias(result.get());
            message.setUser_downCheck("");
            message.setUser_lastDownDate("");
            DAOManager.insertRssTorrent(message);
        }
        System.out.println("즐겨찾기 추가 완료");
    }

}
