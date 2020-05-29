package sample.Controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import sample.Database.PersonQueries;
import sample.Model.HandlesException;
import sample.Model.SceneSwitcher;
import sample.Model.UserTable;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class AdminUpdateController implements Initializable {

   private PersonQueries personQueries = new PersonQueries();



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

   @FXML private Button updateButton;

    @FXML
    private TextField searchName;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ssnTxtField.setEditable(false);
        Tooltip ssnTool = new Tooltip();
        ssnTool.setText("enter your social security number here, it shall be 12 numbers");
        ssnTxtField.setTooltip(ssnTool);

        Tooltip emailTool = new Tooltip();
        emailTool.setText("Enter your email address here");
        emailTxtField.setTooltip(emailTool);

        Tooltip addressTool = new Tooltip();
        addressTool.setText("Type in your living address");
        addressTxtField.setTooltip(addressTool);

        Tooltip passwordTool = new Tooltip();
        passwordTool.setText("Enter your password, it shall contain max 16 characters");
        passwordTxtField.setTooltip(passwordTool);

        Tooltip phoneTool = new Tooltip();
        phoneTool.setText("Enter your phone number. It shall be 13 numbers totally");
        phoneTxtField.setTooltip(phoneTool);

        Tooltip updateTool = new Tooltip();
        updateTool.setText("Press this button after you filled up everything to update");
        updateButton.setTooltip(updateTool);


        show();
        HandlesException handlesException = new HandlesException();
        handlesException.onlyNumber(ssnTxtField);
        handlesException.onlyNumber(phoneTxtField);
        handlesException.onlyLetters(searchName);

        handlesException.inputLimit(passwordTxtField,16);
        handlesException.inputLimit(ssnTxtField,12);
        handlesException.inputLimit(phoneTxtField,13);


        table.setItems(personQueries.getObsList());
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        table.getSelectionModel().selectFirst();


    }

    public void show(){
        tableView();
        personQueries.viewCustomer();
        table.setItems(personQueries.getObsList());
    }

    public void tableView(){
        SSNColumn.setCellValueFactory(new PropertyValueFactory<>("SSN"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phoNumColumn.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("Password"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("Address"));

    }




  @FXML  public void searchKey(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)){
            tableView();
            table.getItems().clear();
            personQueries.personTableSelect(searchName.getText());
            table.setItems(personQueries.getSearchList());

        }

    }

    @FXML public void allButton(){
        table.getItems().clear();
        show();


    }

    @FXML
    public void getSelected(MouseEvent event){
        int index = table.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }
        ssnTxtField.setText(SSNColumn.getCellData(index).toString());
        emailTxtField.setText(emailColumn.getCellData(index).toString());
        addressTxtField.setText(addressColumn.getCellData(index).toString());
        passwordTxtField.setText(passwordColumn.getCellData(index).toString());
        phoneTxtField.setText(phoNumColumn.getCellData(index).toString());
    }

        @FXML
        public void update () {

        try{
            if (ssnTxtField.getText().trim().isEmpty() || phoneTxtField.getText().trim().isEmpty() || passwordTxtField.getText().trim().isEmpty() ||
            addressTxtField.getText().trim().isEmpty() || emailTxtField.getText().trim().isEmpty()){
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setHeaderText("Some fields are empty");
                a.show();

            }

            show();
            personQueries.updatePersonTable(phoneTxtField.getText(), passwordTxtField.getText(), addressTxtField.getText(),
                    emailTxtField.getText(), ssnTxtField.getText());

            table.getItems().clear();
            show();
            phoneTxtField.clear();
            emailTxtField.clear();
            passwordTxtField.clear();
            addressTxtField.clear();
            ssnTxtField.clear();
            Alert b = new Alert(Alert.AlertType.CONFIRMATION);
            b.setHeaderText("Updated");
            b.show();
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
                show();
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
                        show();
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




