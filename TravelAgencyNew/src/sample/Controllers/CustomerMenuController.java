package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sample.Model.SceneSwitcher;

import javax.swing.*;

public class CustomerMenuController {

    @FXML public void viewAndBookPackage(ActionEvent ae){
        SceneSwitcher.SwitchScene(ae, "../View/ViewPackage.fxml");
    }
    @FXML public void backButton(ActionEvent  ae){

        SceneSwitcher.SwitchScene(ae, "../View/Login.fxml");
    }

    @FXML public void openBookingWindow(ActionEvent ae){

        SceneSwitcher.SwitchScene(ae, "../View/ViewPackagesScene.fxml");
    }
}
