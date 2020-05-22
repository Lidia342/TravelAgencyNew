package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sample.Model.Data;
import sample.Model.SceneSwitcher;

public class CustomerMenuController {


    Data myData = Data.getInstance();

    @FXML
    public void viewAndBookPackage(ActionEvent ae) {
        SceneSwitcher.SwitchScene(ae, "../View/ViewPackage.fxml");
    }

    @FXML
    public void backButton(ActionEvent ae) {

        SceneSwitcher.SwitchScene(ae, "../View/Login.fxml");
    }

    @FXML
    public void openBookingWindow(ActionEvent ae) {

        SceneSwitcher.SwitchScene(ae, "../View/ViewPackagesScene.fxml");
    }

    @FXML
    public void signUpAction(ActionEvent ae) {
        SceneSwitcher.SwitchScene(ae, "../View/SignUp.fxml");
    }

    @FXML
    public void loginAction(ActionEvent ae) {
        SceneSwitcher.SwitchScene(ae, "../View/Login.fxml");
    }
    @FXML public void ViewBooking(ActionEvent ae){
        SceneSwitcher.SwitchScene(ae, "../View/ViewBookingScene.fxml");
    }

    @FXML
    public void editInfoAction(ActionEvent ae){
        SceneSwitcher.SwitchScene(ae,"../View/EditInfoScene.fxml" );
    }

}

