
import Database.DatabaseConnection;
import Model.Data;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        DatabaseConnection data = new DatabaseConnection();
        data.connect();
        data.view();

        Parent root = FXMLLoader.load(getClass().getResource("View/main_page.fxml"));
        primaryStage.setTitle("Travel Agency");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

        Data myData= Data.getInstance();
        myData.getDatabaseConnection();
    }
    }


