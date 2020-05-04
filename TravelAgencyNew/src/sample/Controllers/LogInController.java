package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.Database.LogInQueries;
import sample.Model.SceneSwitcher;

import java.io.IOException;

public class LogInController {

    @FXML
    private TextField TfEmail;

    @FXML
    private PasswordField TfPassword;


    Alert e = new Alert(Alert.AlertType.ERROR);
   // Alert c = new Alert(Alert.AlertType.CONFIRMATION);

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
        SceneSwitcher.switchScene(ae,"../View/SignUp.fxml");

        /*Node node = (Node)ae.getSource();
        Stage stage = (Stage)node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/SignUp.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);*/



    }

    @FXML public void adminScene(ActionEvent ae) throws IOException {
        SceneSwitcher.switchScene(ae,"../View/Admin_menu.fxml");
        /*
        Node node = (Node)ae.getSource();
        Stage stage = (Stage)node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/Admin_menu.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);*/



    }

    @FXML public void customerScene(ActionEvent ae) throws IOException {

        SceneSwitcher.switchScene(ae,"../View/CustomerMenu.fxml");

        /*Node node = (Node)ae.getSource();
        Stage stage = (Stage)node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/CustomerMenu.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);*/
    }

}
