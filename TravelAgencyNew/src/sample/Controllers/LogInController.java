package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.Database.LogInQueries;
import sample.Model.SceneSwitcher;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {

    @FXML
    private Button adminButton;

    @FXML
    private TextField TfEmail;

    @FXML
    private Hyperlink createLink;

    @FXML
    private Button cancel;

    @FXML
    private Button customerButton;

    @FXML
    private PasswordField TfPassword;

    Alert e = new Alert(Alert.AlertType.ERROR);


    public void loginException(){
        TfEmail.getText();
        TfPassword.getText();


        if (TfEmail.getText().trim().isEmpty()) {
            e.setTitle("IS empty");
            e.setHeaderText("Empty");
            e.show();
            return;
        }
        if (TfPassword.getText().trim().isEmpty()) {
            e.setTitle("IS empty");
            e.setHeaderText("enter password");
            e.show();
            return;
        }


    }


    @FXML void loginAdmin(ActionEvent ae) throws IOException {
        loginException();
        String emailId = TfEmail.getText();
        String password = TfPassword.getText();


        LogInQueries lq = new LogInQueries();
        boolean flag = lq.validate(emailId, password);

        if (!flag) {
            e.setTitle("Incorrect");
            e.setHeaderText("Enter correct password & press correct button");
            e.show();

        } else {
            adminScene(ae);
        }


    }

    @FXML public void loginCustomer(ActionEvent ae) throws IOException {
        loginException();
        String emailId = TfEmail.getText();
        String password = TfPassword.getText();


        LogInQueries lq = new LogInQueries();
        boolean flag = lq.customerLogin(emailId, password);

        if (!flag) {
            e.setTitle("Incorrect");
            e.setHeaderText("Enter correct password & press correct button");
            e.show();

        } else {
            customerScene(ae);
        }

    }
    @FXML public void cancel(){
        System.exit(0);
    }

    @FXML public void createOneHere(ActionEvent ae) throws IOException {
        SceneSwitcher.SwitchScene(ae,"../View/SignUp.fxml");

    }

    @FXML public void adminScene(ActionEvent ae) throws IOException {
        SceneSwitcher.SwitchScene(ae,"../View/AdminMenu.fxml");
    }

    @FXML public void customerScene(ActionEvent ae) throws IOException {

        SceneSwitcher.SwitchScene(ae,"../View/CustomerMenu.fxml");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Tooltip emailTool = new Tooltip();
        emailTool.setText("Enter your username. The username is the same us email");
        TfEmail.setTooltip(emailTool);

        Tooltip passwordTool = new Tooltip();
        passwordTool.setText("Enter your password here");
        TfPassword.setTooltip(passwordTool);

        Tooltip adminTool = new Tooltip();
        adminTool.setText("Press this button if you are admin to log in");
        adminButton.setTooltip(adminTool);

        Tooltip customerTool = new Tooltip();
        customerTool.setText("Press this button to log in as customer");
        customerButton.setTooltip(customerTool);

        Tooltip createTool = new Tooltip();
        createTool.setText("press here to create an account");
        createLink.setTooltip(createTool);
    }
}
