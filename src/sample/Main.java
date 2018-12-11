package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.text.TableView;
import java.io.IOException;
import java.util.Scanner;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Torrent Rss 검색기");
        primaryStage.setScene(new Scene(root, 1200, 830));
        primaryStage.setResizable(false);
        primaryStage.show();
        Scanner sc = new Scanner(System.in);
        if (sc.next().equals("next")){
            primaryStage.setScene(new Scene(new BorderPane(),1200,830));

        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
