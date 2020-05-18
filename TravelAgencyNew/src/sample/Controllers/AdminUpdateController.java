package sample.Controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import sample.Database.PersonQueries;
import sample.Model.HandlesException;
import sample.Model.SceneSwitcher;
import sample.Model.UserTable;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class AdminUpdateController implements Initializable {

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

    private HandlesException handlesException;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            select();

        handlesException = new HandlesException();
        handlesException.onlyNumber(ssnTxtField);
        handlesException.onlyNumber(phoneTxtField);

        handlesException.inputLimit(passwordTxtField,16);
        handlesException.inputLimit(ssnTxtField,12);
        handlesException.inputLimit(phoneTxtField,13);


        table.setItems(personQueries.getObsList());
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        table.getSelectionModel().selectFirst();


    }


    public void select(){
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
        public void update () {

        try{
            personQueries.updatePersonTable(phoneTxtField.getText(), passwordTxtField.getText(), addressTxtField.getText(),
                    emailTxtField.getText(), ssnTxtField.getText());

            table.getItems().clear();
            select();
            phoneTxtField.clear();
            emailTxtField.clear();
            passwordTxtField.clear();
            addressTxtField.clear();
            ssnTxtField.clear();
        } catch (java.lang.Exception e) {
            System.out.println(e.getMessage());
        }


        }
    public void clicked(ActionEvent event) {

        try{
            Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Person");
            alert.setHeaderText("Delete this person:");
            alert.setContentText("Are you Sure? Press ok to confirm, or cancel to back out.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && (result.get() == ButtonType.OK)){
                UserTable uTable = (UserTable) table.getSelectionModel().getSelectedItem();
                personQueries.removeCustomer(uTable.getSSN());
                table.getItems().clear();
                select();
            }
        } catch (java.lang.Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML public void handleKeyPressed (KeyEvent keyEvent) {
        try{String selectedItem = String.valueOf(table.getSelectionModel().getSelectedItem());
            if (selectedItem != null){
                if (keyEvent.getCode().equals(KeyCode.DELETE)){
                    Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Delete Person");
                    alert.setHeaderText("Delete this person:");
                    alert.setContentText("Are you Sure? Press ok to confirm, or cancel to back out.");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && (result.get() == ButtonType.OK)){
                        UserTable uTable = (UserTable) table.getSelectionModel().getSelectedItem();
                        personQueries.removeCustomer(uTable.getSSN());
                        table.getItems().clear();
                        select();

                    }

                }
            }} catch (java.lang.Exception e) {
            System.out.println(e.getMessage());
        }


    }

    @FXML public void back (ActionEvent ae){

            SceneSwitcher.SwitchScene(ae, "../View/AdminMenu.fxml");

        }

    public void cancel(ActionEvent event) {
        System.exit(0);
    }
}




