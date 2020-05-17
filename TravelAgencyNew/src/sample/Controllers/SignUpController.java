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
import sample.Model.Exception;
import sample.Model.SceneSwitcher;

import java.net.URL;

import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    private Exception exception;

    Alert a = new Alert(Alert.AlertType.ERROR);

    @FXML
    private TextField textFieldSSN, textFieldFirstName, textFieldLastName, textFieldPhoneNum,
            textFieldEmail, textFieldAddress;

    @FXML private PasswordField passwordTextField;
    @FXML private Button buttonCreate;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        exception = new Exception();
        exception.onlyNumber(textFieldSSN);
        exception.onlyLetters(textFieldFirstName);
        exception.onlyLetters(textFieldLastName);
        exception.onlyNumber(textFieldPhoneNum);


        inputLimit(textFieldPhoneNum,13,10);

        exception.fieldsAreEmpty(textFieldSSN, textFieldFirstName, textFieldLastName, textFieldEmail,textFieldPhoneNum, textFieldAddress, passwordTextField, buttonCreate);

    }
    @FXML
    public void create(ActionEvent ae) throws SQLException {

        try{
            PersonQueries personQueries = new PersonQueries();

            boolean save = true;

           if (save) {
                if (personQueries.emailExists(textFieldEmail.getText())) {
                    save = false;
                    a.setTitle("Email");
                    a.setHeaderText("Email already exist! Please try again");
                    a.showAndWait();
                }
            }
            if (save) {
                if (exception.sizePassword(passwordTextField.getText())&& exception.sizePassword1(passwordTextField.getText())) {
                    save = false;
                    a.setTitle("Password");
                    a.setHeaderText("Password should be from 8 to 16 digits or letters");
                    a.showAndWait();
                }
            }
            if (save) {
                if (exception.IsNotPhoneNumber(textFieldPhoneNum.getText())){
                    save = false;
                    a.setTitle("Phone Number");
                    a.setHeaderText("phone number can be 13 numbers");
                    a.showAndWait();
                }
            }
            if (save){
                personQueries.addPersonInformation(textFieldSSN.getText(),textFieldFirstName.getText(),textFieldLastName.getText(),
                        textFieldPhoneNum.getText(),textFieldEmail.getText(),passwordTextField.getText(),textFieldAddress.getText(),
                        "Customer");

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

        }catch (java.lang.Exception e){

            Alert a = new Alert(Alert.AlertType.ERROR);


        }
    }

    public void inputLimit ( final TextField txtFld, final int maxSize, final int minSize){
        txtFld.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (txtFld.getText().length() > maxSize && txtFld.getText().length()< minSize) {
                    String  str = txtFld.getText().substring(0, maxSize);
                    String str1 = txtFld.getText().substring(0, minSize);
                    txtFld.setText(str);
                    txtFld.setText(str1);
                }
            }
        });
    }
    @FXML public void ToAnotherScene(ActionEvent ae){

        SceneSwitcher.SwitchScene(ae, "../View/Login.fxml");

    }
}

