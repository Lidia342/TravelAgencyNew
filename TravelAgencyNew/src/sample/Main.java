package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.Model.Encryption;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/AdminUpdate.fxml"));
        primaryStage.setTitle("Travel Agency");
        primaryStage.centerOnScreen();
        primaryStage.setMinWidth(primaryStage.getWidth());
        primaryStage.setMinHeight(primaryStage.getHeight());
        primaryStage.setScene(new Scene(root));
        Image image = new Image(String.valueOf(getClass().getResource("Images/newLogo.png")));
        primaryStage.getIcons().add(image);
        primaryStage.show();

        Encryption.setKey();

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

