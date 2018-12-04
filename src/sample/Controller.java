package sample;

import FxTable.TableRowDataModel;
import Manager.NyaaFeedParser;
import VO.FileData;
import VO.Nyaa_si_Feed;
import VO.Nyaa_si_FeedMessage;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
    private Button btn_src;


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


//        myTableView.setItems(myList);


    }


    public void btnTest() {
        System.out.println("삭제 결과" + myTableView.getItems().removeAll());
        btn_src.setDisable(true);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                parser = new NyaaFeedParser("https://nyaa.si/?page=rss&q=baki&c=0_0&f=0");
                feed = parser.readFeed();
                List<Nyaa_si_FeedMessage> torrentList = new ArrayList<>();

                try {
                    for (Nyaa_si_FeedMessage message : feed.getMessages()) {
                        parser.saveUrlList(new FileData(message.getTitle(), message.getLink()));
                        torrentList.add(message);
                        System.out.println(message);
//            parser.saveFile();

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
                    btn_src.setDisable(false);
                }
            }
        };
        Thread srcThread = new Thread(runnable);
        srcThread.start();
    }

    public void check_Down() {
        parser.saveFile(6);
    }

    public void btnRss() {
    }

}
