package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sample.Model.SceneSwitcher;

public class CustomerMenuController {




    @FXML public void backButton(ActionEvent  ae){

        SceneSwitcher.SwitchScene(ae, "../View/Login.fxml");
    }
}
