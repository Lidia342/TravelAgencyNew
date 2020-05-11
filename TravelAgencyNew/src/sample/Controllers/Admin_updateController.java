package sample.Controllers;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Database.PersonQueries;
import sample.Model.*;
import sample.Model.Exception;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class Admin_updateController implements Initializable {

   PersonQueries personQueries = new PersonQueries();


    @FXML
    private TableView<Object> table;

    @FXML
    private TableColumn<UserTable, String> SSNColumn;

    @FXML
    private TableColumn<UserTable, String> firstNameColumn;

    @FXML
    private TableColumn<UserTable, String> lastNameColumn;

    @FXML
    private TableColumn<UserTable, String> phoNumColumn;

    @FXML
    private TableColumn<UserTable, String> emailColumn;

    @FXML
    private TableColumn<UserTable, String> passwordColumn;

    @FXML
    private TableColumn<UserTable, String> addressColumn;


    @FXML
    private TextField passwordTxtField;
    @FXML
    private TextField emailTxtField;

    @FXML
    private TextField ssnTxtField;

    @FXML
    private TextField phoneTxtField;

    @FXML
    private TextField addressTxtField;


    private Exception exception;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        exception = new Exception();
        exception.onlyNumber(ssnTxtField);
        exception.onlyNumber(phoneTxtField);

        inputLimit(passwordTxtField,16);
        inputLimit(ssnTxtField,12);
        inputLimit(phoneTxtField,13);

        SSNColumn.setCellValueFactory(new PropertyValueFactory<>("SSN"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phoNumColumn.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("Password"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("Address"));
    }


    @FXML public void select() throws SQLException {


            SSNColumn.setCellValueFactory(new PropertyValueFactory<>("SSN"));
            firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            phoNumColumn.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
            passwordColumn.setCellValueFactory(new PropertyValueFactory<>("Password"));
            addressColumn.setCellValueFactory(new PropertyValueFactory<>("Address"));

        personQueries.personTableSelect();
        table.setItems(personQueries.getObsList());
    }

        @FXML
        public void update () throws SQLException {

            personQueries.updatePersonTable(phoneTxtField.getText(), passwordTxtField.getText(), addressTxtField.getText(),
                    emailTxtField.getText(), ssnTxtField.getText());

            table.getItems().clear();
            select();
            phoneTxtField.clear();
            emailTxtField.clear();
            passwordTxtField.clear();
            addressTxtField.clear();
            ssnTxtField.clear();


        }


        @FXML public void backButton (ActionEvent ae){

            SceneSwitcher.SwitchScene(ae, "../View/Admin_menu.fxml");

        }
        public void inputLimit ( final TextField txtFld, final int maxSize){
            txtFld.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                    if (txtFld.getText().length() > maxSize) {
                        String str = txtFld.getText().substring(0, maxSize);
                        txtFld.setText(str);

                    }
                }
            });
        }

}




