package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sample.Model.SceneSwitcher;

public class AdminMenuController {


    @FXML
    public void updateCustomer(ActionEvent ab) {SceneSwitcher.SwitchScene(ab,"../View/AdminUpdate.fxml"); }

    @FXML
    public void editPackage(ActionEvent ae) {
        SceneSwitcher.SwitchScene(ae,"../View/AdminPackage.fxml");
    }

    @FXML
    public void editBookings(ActionEvent ae) { SceneSwitcher.SwitchScene(ae,"../View/ViewBookedAdmin.fxml");}




    @FXML
    public void back(ActionEvent ae) {
        SceneSwitcher.SwitchScene(ae,"../View/Login.fxml");

    }

    public void cancel(ActionEvent event) { System.exit(0); }
}
