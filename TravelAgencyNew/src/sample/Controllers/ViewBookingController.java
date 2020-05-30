package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;
import sample.Model.Data;
import sample.Model.Email;
import sample.Model.SceneSwitcher;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewBookingController implements Initializable {

    Data myData = Data.getInstance();
    @FXML
    Text packageName, destinationName, flightDepDate, flightRetDate, hotelName, hotelNights, carModel, carRentDays, customerName;
    @FXML
    ToggleButton closeButton, emailButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        customerName.setText((myData.getUser().getFirstName())+myData.getUser().getLastName());
        packageName.setText(myData.getCurrentPackage().getPackageName());
        destinationName.setText(myData.getCurrentPackage().getArrivingCity());
        flightDepDate.setText(String.valueOf(myData.getCurrentPackage().getDeparture_time()));
        flightRetDate.setText(String.valueOf(myData.getCurrentPackage().getReturn_time()));
        hotelName.setText(myData.getCurrentPackage().getHotelName());
        hotelNights.setText(String.valueOf(myData.getCurrentPackage().getHotelNights()));
        carModel.setText(myData.getCurrentPackage().getCarModel());
        carRentDays.setText(String.valueOf(myData.getCurrentPackage().getCarRentalDays()));

        //Booking newBooking = new Booking();
        //myData.setBooking();
    }

    @FXML
    private void closeStage(ActionEvent event) throws IOException {

        SceneSwitcher.SwitchScene(event, "../View/CustomerMenu.fxml");
    }

    @FXML
    private void emailReminder(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Would you like a confirmation to be emailed to you?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            Email.sendEmail(myData.getUser().getEmail(), "reminder");
            alert.close();
        }
        else {
            alert.close();
            return;
        }
    }
}
