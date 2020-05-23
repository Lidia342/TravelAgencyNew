package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import sample.Model.SceneSwitcher;

import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void login(ActionEvent event) {
        SceneSwitcher.SwitchScene(event, "../View/Login.fxml");
    }

    public void signUp(ActionEvent event) {
        SceneSwitcher.SwitchScene(event, "../View/SignUp.fxml");
    }
}
