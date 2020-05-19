package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import sample.Database.PersonQueries;
import sample.Model.Data;
import sample.Model.SceneSwitcher;

import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class EditInformationController implements Initializable {

        Data myData = Data.getInstance();
        @FXML
        Button backButton;
        @FXML
        Label firstNameLabel, lastNameLabel, emailLabel;
        @FXML
        Label firstNameLabelNew, lastNameLabelNew, emailLabelNew;
        @FXML
        TextField updateFirstName, updateLastName, updateEmail, updatePassword;

        PersonQueries personQueries = new PersonQueries();

        @Override
        public void initialize(URL location, ResourceBundle resources) {

            try {
                personalizeScene();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @FXML
        public void editUpdateText(KeyEvent e) {

            TextField field = (TextField) e.getSource();
            if (field.getText().length() > 30)
                field.setEditable(false);

            if (e.getCode().equals(KeyCode.BACK_SPACE))
                field.setEditable(true);

            switch (((Node) e.getSource()).getId()) {
                case "updateFirstName":
                    firstNameLabelNew.setText(updateFirstName.getText());
                    break;
                case "updateLastName":
                    lastNameLabelNew.setText(updateLastName.getText());
                    break;
                case "updateEmail":
                    emailLabelNew.setText(updateEmail.getText());
                    break;
            }
        }


        @FXML
        public void saveClick(ActionEvent event) throws IOException, GeneralSecurityException {

            String userType = myData.getUser().getType();

            Alert alertConf = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to update your information?", ButtonType.NO, ButtonType.YES);
            alertConf.showAndWait();
            if(alertConf.getResult() == ButtonType.NO){
                alertConf.close();
                return;
            } else {
                if (!updateEmail.getText().trim().isEmpty()) {
                    if(emailValidator()){

                        try {
                            personQueries.editEmail(emailLabelNew.getText(), myData.getUser().getSSN());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        Alert alert = new Alert(Alert.AlertType.WARNING, "The email must be of this format: \"example01@ucl.com\"!", ButtonType.OK);
                        alert.showAndWait();
                        if(alert.getResult() == ButtonType.OK){
                            return;
                        }
                    }
                }
                if (!updateFirstName.getText().trim().isEmpty()) {
                    try {
                        personQueries.editFirstName(updateFirstName.getText(), myData.getUser().getSSN());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (!updateLastName.getText().trim().isEmpty()) {
                    try {
                        personQueries.editLastName(updateLastName.getText(), myData.getUser().getSSN());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (!updatePassword.getText().trim().isEmpty()) {
                    try {
                        personQueries.editPassword(updatePassword.getText(), myData.getUser().getSSN());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                alertConf.close();
                SceneSwitcher.SwitchScene(event,"/scenes/UserScene.fxml");
            }
        }

        @FXML
        public void backToPrevScene(ActionEvent event) throws IOException {

            SceneSwitcher.SwitchScene(event, "/scenes/UserScene.fxml");
        }

        protected void personalizeScene() throws IOException {

            firstNameLabel.setText(myData.getUser().getFirstName());
            lastNameLabel.setText(myData.getUser().getLastName());
            emailLabel.setText(myData.getUser().getEmail());

            firstNameLabelNew.setText(firstNameLabel.getText());
            lastNameLabelNew.setText(lastNameLabel.getText());
            emailLabelNew.setText(emailLabel.getText());
        }


        @FXML
        boolean emailValidator () {
            return Pattern.matches("[A-Za-z0-9/.]+([/@])[A-Za-z0-9]+[/.][A-Za-z/.]+", (CharSequence) updateEmail.getText());
        }



}

