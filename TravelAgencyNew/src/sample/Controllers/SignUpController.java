package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.Database.PersonQueries;
import sample.Model.HandlesException;
import sample.Model.SceneSwitcher;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    private HandlesException handlesException;

    Alert a = new Alert(Alert.AlertType.ERROR);


    @FXML
    private TextField textFieldSSN, textFieldFirstName, textFieldLastName, textFieldPhoneNum,
            textFieldEmail, textFieldAddress;

    @FXML private PasswordField passwordTextField;
    @FXML private Button buttonCreate;
    @FXML private Button loginbutton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Tooltip ssntool = new Tooltip();
        ssntool.setText("enter your social security number here, it shall be 12 numbers");
        textFieldSSN.setTooltip(ssntool);

        Tooltip firstNametool = new Tooltip();
        firstNametool.setText("Enter your first name");
        textFieldFirstName.setTooltip(firstNametool);

        Tooltip lastnametool = new Tooltip();
        lastnametool.setText("Enter your last name");
        textFieldLastName.setTooltip(lastnametool);

        Tooltip phoneTool = new Tooltip();
        phoneTool.setText("Enter your phone number. It shall be 13 numbers totally");
        textFieldPhoneNum.setTooltip(phoneTool);

        Tooltip emailTool = new Tooltip();
        emailTool.setText("Enter your email address here");
        textFieldEmail.setTooltip(emailTool);

        Tooltip addressTool = new Tooltip();
        addressTool.setText("Type in your living address");
        textFieldAddress.setTooltip(addressTool);

        Tooltip passwordTool = new Tooltip();
        passwordTool.setText("Enter your password, it shall contain max 16 characters");
        passwordTextField.setTooltip(passwordTool);

        Tooltip createTool = new Tooltip();
        createTool.setText("Press this button to create the account");
        buttonCreate.setTooltip(createTool);

        Tooltip loginTool = new Tooltip();
        loginTool.setText("Press this button to log in");
        loginbutton.setTooltip(loginTool);

        handlesException = new HandlesException();
        handlesException.onlyNumber(textFieldSSN);
        handlesException.onlyLetters(textFieldFirstName);
        handlesException.onlyLetters(textFieldLastName);
        handlesException.onlyNumber(textFieldPhoneNum);

        handlesException.inputLimit(textFieldSSN,12);
        handlesException.inputLimit(textFieldPhoneNum,13);
        handlesException.inputLimit(passwordTextField,16);

        handlesException.emptyTxtFields(textFieldSSN, textFieldFirstName, textFieldLastName, textFieldEmail,
                textFieldPhoneNum, textFieldAddress, passwordTextField, buttonCreate);

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
                if (handlesException.sizePassword(passwordTextField.getText())) {
                    save = false;
                    a.setTitle("Password");
                    a.setHeaderText("Password should be from 8 to 16 digits or letters");
                    a.showAndWait();
                }
            }
            if (save) {
                if (handlesException.IsNotPhoneNumber(textFieldPhoneNum.getText())){
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
    @FXML public void ToAnotherScene(ActionEvent ae){

        SceneSwitcher.SwitchScene(ae, "../View/Login.fxml");

    }
}

