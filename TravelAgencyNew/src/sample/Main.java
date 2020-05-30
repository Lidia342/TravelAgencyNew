package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.Database.BookingQueries;
import sample.Model.Encryption;
import sample.Model.PdfFile;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/mainPage.fxml"));
        primaryStage.setTitle("Travel Agency");
        primaryStage.centerOnScreen();
        //primaryStage.setMaximized(true);
        primaryStage.setMinWidth(primaryStage.getWidth());
        primaryStage.setMinHeight(primaryStage.getHeight());
        primaryStage.setScene(new Scene(root));
        Image image = new Image(String.valueOf(getClass().getResource("Images/newLogo.png")));
        primaryStage.getIcons().add(image);
        primaryStage.show();
        PdfFile pf = new PdfFile();
        Encryption.setKey();
      //  pf.createPdfFile();
        BookingQueries bq = new BookingQueries();
       // bq.customerBookingInfo();
        for (int i = 0; i <bq.getCusBookingInfo().size() ; i++) {
            System.out.println(bq.getCusBookingInfo().get(i));

        }
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

