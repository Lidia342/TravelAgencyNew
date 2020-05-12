package sample.Controllers;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import sample.Database.BookingQueries;
import sample.Main;
import sample.Model.Booking;
import sample.Model.Data;
import sample.Model.Package;
import sample.Model.SceneSwitcher;

public class ViewSelectedPackageController implements Initializable {

    Package selectedPackage = null;
    File imgFile = null;
    Desktop desktop = Desktop.getDesktop();
    Data myData = Data.getInstance();
    BookingQueries aup;

    @FXML
    ImageView selectedPackageImg;
    @FXML
    Text title;
    @FXML
    Text description;
    @FXML
    Text startDate;
    @FXML
    Text endDate;
    @FXML
    Text time;
    @FXML
    Button backButton, bookButton, deleteFilmButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        /*
        if(Main.isEmployee())
            bookButton.setText("Go to Bookings");
         */

        selectedPackage = BookingQueries.getPackage();
        try {
            String path = URLDecoder.decode(Main.getPath() + "sample/Images/PackageImages/", "UTF-8");

            imgFile = new File(path + "Honeymoon1" + ".png");
            Image img = SwingFXUtils.toFXImage(ImageIO.read(imgFile), null);
            selectedPackageImg.setImage(img);
        } catch (IOException | NullPointerException e) {
            try {
                String path = URLDecoder.decode(Main.getPath() + "sample/Images/PackageImages/", "UTF-8");
                imgFile = new File(path + "Honeymoon1" + ".jpg");
                Image img = SwingFXUtils.toFXImage(ImageIO.read(imgFile), null);
                //selectedPackageImg.setImage(img);
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
//        title.setText(selectedPackage.getName());
        /*
        description.setText(selectedPackage.getDescription());
        startDate.setText(selectedPackage.getStartDate());
        endDate.setText(selectedPackage.getEndDate());
         String displayedTimes = "";
        for (int i = 0; i < selectedFilm.getTimes().length; i++) {
            if (!selectedFilm.getTimes()[i].equals("hh:mm"))
                displayedTimes += selectedFilm.getTimes()[i] + ", ";
        }

        time.setText(displayedTimes.substring(0, displayedTimes.length() - 2));
         */

        if (myData.getUser().getType()=="Employee") {
            deleteFilmButton.setVisible(false);
            bookButton.setVisible(false);

        }
    }

    @FXML
    public void deletePackage(ActionEvent event) throws IOException {

        Alert alert = new Alert(AlertType.CONFIRMATION, "Do you want to delete this travel package?", ButtonType.NO, ButtonType.YES);
        alert.showAndWait();

        if(alert.getResult() == ButtonType.YES) {

            imgFile.delete();
            aup.deletePackage();
            backToPrevScene(event);
        }
        else {
            return;
        }
    }

    @FXML
    public void goToBookingScene(ActionEvent event) throws IOException {

        SceneSwitcher.SwitchScene(event, "/scenes/ManageBookingsScene.fxml");
    }

    @FXML
    public void backToPrevScene(ActionEvent event) throws IOException {

        SceneSwitcher.SwitchScene(event, "/scenes/ViewFilmsScene.fxml");
    }
}