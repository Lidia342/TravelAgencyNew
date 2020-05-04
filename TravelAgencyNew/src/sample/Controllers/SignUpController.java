package sample.Controllers;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.Database.PersonQueries;
import sample.Model.PersonalInfoException;
import sample.Model.SceneSwitcher;

import java.net.URL;

import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    private PersonalInfoException personalInfoException;

    Alert a = new Alert(Alert.AlertType.ERROR);



    @FXML
    private TextField textFieldSSN, textFieldFirstName, textFieldLastName, textFieldPhoneNum,
            textFieldEmail, textFieldAddress;

    @FXML private PasswordField passwordTextField;
    @FXML private Button buttonCreate;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        personalInfoException = new PersonalInfoException();
        personalInfoException.onlyNumber(textFieldSSN);
        personalInfoException.onlyLetters(textFieldFirstName);
        personalInfoException.onlyLetters(textFieldLastName);
        personalInfoException.onlyNumber(textFieldPhoneNum);


        textLimiter(textFieldPhoneNum,13);

        personalInfoException.fieldsAreEmpty(textFieldSSN, textFieldFirstName, textFieldLastName, textFieldEmail,textFieldPhoneNum, textFieldAddress, passwordTextField, buttonCreate);

    }
    @FXML
    public void create(ActionEvent ae) throws SQLException {

        try{
            PersonQueries personQueries = new PersonQueries();

            boolean allowSave = true;

            if (allowSave) {
                if (personQueries.emailExists(textFieldEmail.getText())) {
                    allowSave = false;
                    a.setTitle("Email");
                    a.setHeaderText("Email already exist! Please try again");
                    a.showAndWait();
                }
            }
            if (allowSave) {
                if (personalInfoException.lengthOfPassword(passwordTextField.getText())&&personalInfoException.lengthOfPassword1(passwordTextField.getText())) {
                    allowSave = false;
                    a.setTitle("Password");
                    a.setHeaderText("Password should be from 8 to 16 digits or letters");
                    a.showAndWait();
                }
            }
            if (allowSave) {
                if (personalInfoException.IsNotPhoneNumber(textFieldPhoneNum.getText())){
                    allowSave = false;
                    a.setTitle("Phone Number");
                    a.setHeaderText("phone number can be 13 numbers");
                    a.showAndWait();
                }
            }
            if (allowSave){
                personQueries.addPersonInformation(textFieldSSN.getText(),textFieldFirstName.getText(),textFieldLastName.getText(),
                        textFieldPhoneNum.getText(),textFieldEmail.getText(),passwordTextField.getText(),textFieldAddress.getText());

                textFieldEmail.clear();
                textFieldFirstName.clear();
                textFieldLastName.clear();
                passwordTextField.clear();
                textFieldPhoneNum.clear();
                textFieldSSN.clear();
                textFieldAddress.clear();

                a.setHeaderText("connected!");
                a.showAndWait();
            }

        }catch (Exception e){

            Alert a = new Alert(Alert.AlertType.ERROR);


        }
    }

    public void textLimiter ( final TextField tf, final int maxLength){
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tf.getText().length() > maxLength&& tf.getText().length()<16) {
                    String s = tf.getText().substring(0, maxLength);
                    tf.setText(s);
                }
            }
        });
    }
    @FXML public void ToAnotherScene(ActionEvent ae){

        SceneSwitcher.switchScene(ae, "../View/Login.fxml");

    }
}

