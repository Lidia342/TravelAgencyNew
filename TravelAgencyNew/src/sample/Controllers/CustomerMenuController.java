package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sample.Model.SceneSwitcher;

import javax.swing.*;

public class CustomerMenuController {




    @FXML public void backButton(ActionEvent  ae){

        SceneSwitcher.SwitchScene(ae, "../View/Login.fxml");
    }

    @FXML public void openBookingWindow(ActionEvent ae){

        SceneSwitcher.SwitchScene(ae, "../View/flight_selection_scene.fxml");
    }
}
