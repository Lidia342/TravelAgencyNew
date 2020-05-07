package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import sample.Model.SceneSwitcher;
import sample.Model.UserTable;


import java.sql.*;

public class Admin_deleteController {



    String url1 = "jdbc:mysql://den1.mysql5.gear.host:3306/travelagency1";
    String userName1 = "travelagency1";
    String password1 = "Cw0Mr?!4KN2V";
    private Connection connection;
    ResultSet resultSet;
    PreparedStatement pt;
    private ObservableList<UserTable> obsList = FXCollections.observableArrayList();
    @FXML private TableView <UserTable> table;
    @FXML private TableColumn <UserTable, String> ssnColumn;
    @FXML private TableColumn <UserTable, String> firstNameColumn;
    @FXML private TableColumn <UserTable, String> lastNameColumn;
    @FXML private TableColumn <UserTable, String> phoneNumberColumn;
    @FXML private TableColumn <UserTable, String> emailColumn;
    @FXML private TableColumn <UserTable, String> passwordColumn;
    @FXML private TableColumn <UserTable, String> addressColumn;
    @FXML private TextField deleteTextField;


    @FXML public void select() throws SQLException {
       connection = DriverManager.getConnection(url1,
        userName1,password1 );

        String selectQuery = "SELECT * FROM user WHERE SSN != 199205134561";

        resultSet = connection.createStatement().executeQuery(selectQuery);


        ssnColumn.setCellValueFactory(new PropertyValueFactory<>("SSN"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("Password"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("Address"));


       while (resultSet.next()){
            UserTable ut = new UserTable("SSN", "firstName", "lastName",
                    "phoneNumber","email", "password", "address","type");


            ut.setSSN(resultSet.getString("SSN"));
            ut.setFirstName(resultSet.getString("firstName"));
            ut.setLastName(resultSet.getString("lastName"));
            ut.setPhoneNumber(resultSet.getString("phoneNumber"));
            ut.setEmail(resultSet.getString("email"));
            ut.setPassword(resultSet.getString("password"));
            ut.setAddress(resultSet.getString("address"));

           obsList.add(ut);


       }
        table.setItems(obsList);
    }




    @FXML public void remove() throws SQLException {

        connection = DriverManager.getConnection(url1,
                userName1,password1 );
        try {
            String updateQuery = "DELETE FROM user WHERE SSN = ?";
            pt = connection.prepareStatement(updateQuery);
            pt.setString(1, deleteTextField.getText());
            pt.executeUpdate();
            table.getItems().clear();
            select();
            deleteTextField.clear();
        } catch (SQLException e) {

        }


    }
    @FXML public void backButton(ActionEvent ae){
        SceneSwitcher.SwitchScene(ae,"../View/Admin_menu.fxml");

    }


}

