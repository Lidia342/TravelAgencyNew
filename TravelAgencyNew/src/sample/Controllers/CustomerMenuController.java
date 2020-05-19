package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sample.Model.Data;
import sample.Model.SceneSwitcher;
import javafx.fxml.Initializable;


import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerMenuController implements Initializable {

    Data myData = Data.getInstance();
    @FXML
    Button loginButton;

    @FXML public void viewAndBookPackage(ActionEvent ae){
        SceneSwitcher.SwitchScene(ae, "../View/ViewPackage.fxml");
    }

    @FXML public void backButton(ActionEvent  ae){

    }

    @FXML public void openBookingWindow(ActionEvent ae){

        SceneSwitcher.SwitchScene(ae, "../View/ViewPackagesScene.fxml");
    }

    public void logIn(ActionEvent actionEvent) {
        SceneSwitcher.SwitchScene(actionEvent, "../View/Login.fxml");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(myData.getUser()!=null){
            loginButton.setText("Edit Info");
            loginButton.setOnMouseClicked(event -> {

                SceneSwitcher.SwitchMouseScene(event, "../View/EditInfoScene.fxml");
            });
        }
    }
}
