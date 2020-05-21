package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import sample.Model.Data;
import sample.Model.SceneSwitcher;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerMenuController implements Initializable {

    @FXML Button loginButton;
    @FXML Button editInfoButton;


    Data myData= Data.getInstance();

    @FXML public void viewAndBookPackage(ActionEvent ae){
        SceneSwitcher.SwitchScene(ae, "../View/ViewPackage.fxml");
    }
    @FXML public void backButton(ActionEvent  ae){

        SceneSwitcher.SwitchScene(ae, "../View/Login.fxml");
    }

    @FXML public void openBookingWindow(ActionEvent ae){

        SceneSwitcher.SwitchScene(ae, "../View/ViewPackagesScene.fxml");
    }

    @FXML public void signUpAction(ActionEvent ae){
        SceneSwitcher.SwitchScene(ae, "../View/SignUp.fxml");
    }

    @FXML public void loginAction(ActionEvent ae){
        SceneSwitcher.SwitchScene(ae, "../View/Login.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            if (myData.getUser() != null) {

                editInfoButton.setVisible(true);
                loginButton.setVisible(false);
            } else {
                editInfoButton.setVisible(false);
                loginButton.setVisible(true);
            }
        }
        catch (NullPointerException e){}
    }

}
