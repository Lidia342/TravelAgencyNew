package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sample.Model.SceneSwitcher;

public class HelpMenuController {

    @FXML
    public void backtoMenu(ActionEvent ae){

        SceneSwitcher.SwitchScene(ae, "../View/CustomerMenu.fxml");
    }
}
