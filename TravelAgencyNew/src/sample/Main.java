package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/MainPage.fxml"));
        primaryStage.setTitle("Travel Agency");
        primaryStage.centerOnScreen();
        //primaryStage.setMaximized(true);
        primaryStage.setMinWidth(primaryStage.getWidth());
        primaryStage.setMinHeight(primaryStage.getHeight());
        primaryStage.setScene(new Scene(root));


        //Image image = new Image("C:/Users/Lili/Desktop/Travel/TravelAgencyNew/src/sample/Images.png");
        //primaryStage.getIcons().add(image);


        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }

    public static String getPath() {

        String path = ClassLoader.getSystemClassLoader().getResource(".").getPath();
        if (path.contains("zcinema/bin"))
            path = path.split("zcinema")[0];
        return path;
    }
}

