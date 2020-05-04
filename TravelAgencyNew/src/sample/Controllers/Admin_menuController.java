package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sample.Model.SceneSwitcher;

import java.io.IOException;

public class Admin_menuController {


    @FXML
    public void updateCustomer(ActionEvent ab) throws IOException {
        SceneSwitcher.SwitchScene(ab,"../View/Admin_update.fxml");
    }

    @FXML
    public void deleteCustomer(ActionEvent ae) {
        SceneSwitcher.SwitchScene(ae,"../View/Admin_delete.fxml");
    }

    @FXML
    public void editBookings(ActionEvent ae) {

        SceneSwitcher.SwitchScene(ae,"../View/EditBooking.fxml");
    }

    @FXML
    public void deleteBooking(ActionEvent ae) {
       SceneSwitcher.SwitchScene(ae,"../View/deleteBooking.fxml");

    }

    @FXML
    public void viewPayment(ActionEvent ae) {
       SceneSwitcher.SwitchScene(ae,"../View/ViewPayment.fxml");

    }

    @FXML
    public void backButton(ActionEvent ae) {
        SceneSwitcher.SwitchScene(ae,"../View/Login.fxml");

    }
}
