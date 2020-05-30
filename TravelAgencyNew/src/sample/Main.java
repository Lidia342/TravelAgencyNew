package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.Database.BookingQueries;
import sample.Model.Encryption;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("View/mainPage.fxml"));
        primaryStage.setTitle("Travel Agency");
        primaryStage.centerOnScreen();
        primaryStage.setMinWidth(primaryStage.getWidth());
        primaryStage.setMinHeight(primaryStage.getHeight());
        primaryStage.setScene(new Scene(root));
        Image image = new Image(String.valueOf(getClass().getResource("Images/newLogo.png")));
        primaryStage.getIcons().add(image);
        primaryStage.show();

        Encryption.setKey();
        System.out.println(Encryption.encrypt("miniane321"));
        //  pf.createPdfFile();
        BookingQueries bq = new BookingQueries();
        // bq.customerBookingInfo();
        for (int i = 0; i < bq.getCusBookingInfo().size(); i++) {
            System.out.println(bq.getCusBookingInfo().get(i));

        }
    }

    public static void main(String [] args) {
        launch(args);
    }

}

