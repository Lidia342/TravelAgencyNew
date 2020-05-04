package sample.Controllers;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    private String url1 = "jdbc:mysql://den1.mysql5.gear.host:3306/travelagency1";
    private String userName1 = "travelagency1";
    private String password1 = "Cw0Mr?!4KN2V";
    private Connection connection;
    private ObservableList<UserTable> obsList = FXCollections.observableArrayList();
    @FXML
    private TableView<UserTable> table;

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
    @FXML private TableColumn<UserTable, String> typecoloum;

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

    @FXML
    private Button uppdatebutton;


    private Exception exception;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exception = new Exception();
        exception.onlyNumber(ssnTxtField);
        exception.onlyNumber(phoneTxtField);


        inputLimit(phoneTxtField,13);

        personQueries.personTableSelect();

        SSNColumn.setCellValueFactory(new PropertyValueFactory<>("SSN"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phoNumColumn.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("Password"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("Address"));
        typecoloum.setCellValueFactory(new PropertyValueFactory<>("type"));


    }


    @FXML public void select() throws SQLException {


            SSNColumn.setCellValueFactory(new PropertyValueFactory<>("SSN"));
            firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            phoNumColumn.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
            passwordColumn.setCellValueFactory(new PropertyValueFactory<>("Password"));
            addressColumn.setCellValueFactory(new PropertyValueFactory<>("Address"));
            typecoloum.setCellValueFactory(new PropertyValueFactory<>("type"));

        connection = DriverManager.getConnection(url1,
                userName1, password1);

        String selectQuery = "SELECT * FROM user WHERE SSN != 199205134561";

        ResultSet resultSet = connection.createStatement().executeQuery(selectQuery);
            while (resultSet.next()) {
                UserTable ut = new UserTable("SSN", "firstName", "lastName",
                        "phoneNumber", "email", "password", "address", "type");


                ut.setSSN(resultSet.getString("SSN"));
                ut.setFirstName(resultSet.getString("firstName"));
                ut.setLastName(resultSet.getString("lastName"));
                ut.setPhoneNumber(resultSet.getString("phoneNumber"));
                ut.setEmail(resultSet.getString("email"));
                ut.setPassword(resultSet.getString("password"));
                ut.setAddress(resultSet.getString("address"));
                ut.setType(resultSet.getString("type"));

                obsList.add(ut);


            }

            table.setItems(obsList);

        }


        @FXML
        public void update () throws SQLException {
            connection = DriverManager.getConnection(url1, userName1, password1);

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




