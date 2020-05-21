package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import sample.Database.LogInQueries;
import sample.Model.Data;
import sample.Model.HandlesException;
import sample.Model.SceneSwitcher;
import sample.Model.User;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class LogInController implements Initializable {
    private HandlesException handlesException;
    Data myDate = new Data();

    @FXML
    private Button logInButton;

    @FXML
    private TextField TfEmail;

    @FXML
    private Hyperlink createLink;

    @FXML
    private Button cancel;

    @FXML
    private Button customerButton;

    @FXML private PasswordField TfPassword;

    @FXML
    private Hyperlink forgotPassword;
    @FXML
    private AnchorPane
            mainBorderPane;

    Alert e = new Alert(Alert.AlertType.ERROR);



        @FXML void login (ActionEvent ae) throws IOException {
            String emailId = TfEmail.getText();
            String password = TfPassword.getText();

            LogInQueries logInQueries = new LogInQueries();
            boolean flag = logInQueries.validate(emailId, password);
            boolean hello = logInQueries.customerLogin(emailId, password);

            if (!hello && !flag) {
                Alert f = new Alert(Alert.AlertType.ERROR);
                f.setTitle("Incorrect");
                f.setHeaderText("Enter correct password");
                f.show();
                TfPassword.clear();
                TfEmail.clear();

            } else if (hello) {
                customerScene(ae);
                User currentUser = logInQueries.establishCurrentCustomer(emailId, password);
                myDate.setUser(currentUser);
            } else {
                adminScene(ae);
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
    @FXML public void showDialog(ActionEvent ae) throws IOException {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        try{
            Parent root = FXMLLoader.load(getClass().getResource("../View/ResetPassword.fxml"));
            dialog.getDialogPane().setContent(root);

        } catch (IOException ex) {
            System.out.println("Couldn't load the dialog");
            ex.printStackTrace();
            return;
        }
        //dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        handlesException = new HandlesException();
        handlesException.emptyTxtFields1(TfEmail,TfPassword,logInButton);

        Tooltip emailTool = new Tooltip();
        emailTool.setText("Enter your email here");
        TfEmail.setTooltip(emailTool);

        Tooltip passwordTool = new Tooltip();
        passwordTool.setText("Enter your password here");
        TfPassword.setTooltip(passwordTool);

        Tooltip adminTool = new Tooltip();
        adminTool.setText("Press this button if you want to log in");
        logInButton.setTooltip(adminTool);


        Tooltip createTool = new Tooltip();
        createTool.setText("press here to create an account");
        createLink.setTooltip(createTool);
    }
}
