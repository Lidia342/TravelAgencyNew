package sample.Model;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneSwitcher {
    public static void SwitchScene(ActionEvent event,String path){
        try{
            ((Stage)(((Node)event.getSource()).getScene().getWindow())).setScene(
                    new Scene(FXMLLoader.load(SceneSwitcher.class.getResource(path)))
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
