package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import sample.Database.BookingQueries;
import sample.Model.Data;
import sample.Model.SceneSwitcher;

import java.sql.SQLException;

public class CustomerMenuController {

    BookingQueries bQ = new BookingQueries();
    Data myData = Data.getInstance();


    @FXML public void viewAndBookPackage(ActionEvent ae){
        SceneSwitcher.SwitchScene(ae, "../View/ViewPackage.fxml");

    }

    @FXML
    public void backButton(ActionEvent ae) {

        SceneSwitcher.SwitchScene(ae, "../View/Login.fxml");
    }

    @FXML
    public void editInfoAction(ActionEvent ae){
        SceneSwitcher.SwitchScene(ae,"../View/EditInfoScene.fxml" );
    }

    public void cancel(ActionEvent event) {
        System.exit(0);
    }

    public void logOutAction(ActionEvent actionEvent) {
        SceneSwitcher.SwitchScene(actionEvent, "../View/MainPage.fxml");
    }

    public void helpMenuAction(MouseEvent event) {
        SceneSwitcher.SwitchMouseScene(event, "../View/HelpMenu.fxml");
    }

    public void viewBookedPackage(ActionEvent actionEvent) {

        try {
            myData.setCurrentBooking(bQ.retreiveBooking(myData.getUser().getSSN()));
            SceneSwitcher.SwitchScene(actionEvent, "../View/ViewBookingCustomer.fxml");
        } catch (SQLException |NullPointerException e) {
            Alert f = new Alert(Alert.AlertType.ERROR);
            f.setTitle("No booking found");
            f.setHeaderText("You have not booked any package yet!");
            f.show();
        }
    }
}

