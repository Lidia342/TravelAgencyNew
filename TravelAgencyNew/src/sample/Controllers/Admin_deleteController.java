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

import sample.Model.PersonTable;
import sample.Model.SceneSwitcher;


import java.sql.*;

public class Admin_deleteController {



    String url1 = "jdbc:mysql://den1.mysql5.gear.host:3306/travelagency1";
    String userName1 = "travelagency1";
    String password1 = "Cw0Mr?!4KN2V";
    private Connection connection;
    ResultSet resultSet;
    PreparedStatement pt;
    private ObservableList<PersonTable> obsList = FXCollections.observableArrayList();
    @FXML private TableView <PersonTable> table;
    @FXML private TableColumn <PersonTable, String> ssnColumn;
    @FXML private TableColumn <PersonTable, String> firstNameColumn;
    @FXML private TableColumn <PersonTable, String> lastNameColumn;
    @FXML private TableColumn <PersonTable, String> phoneNumberColumn;
    @FXML private TableColumn <PersonTable, String> emailColumn;
    @FXML private TableColumn <PersonTable, String> passwordColumn;
    @FXML private TableColumn <PersonTable, String> addressColumn;
    @FXML private TextField deleteTextField;


    @FXML public void select() throws SQLException {
       connection = DriverManager.getConnection(url1,
        userName1,password1 );

        String selectQuery = "SELECT * FROM person WHERE SSN != 199205134561";

        resultSet = connection.createStatement().executeQuery(selectQuery);


        ssnColumn.setCellValueFactory(new PropertyValueFactory<>("SSN"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("Password"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("Address"));


       while (resultSet.next()){
            PersonTable ut = new PersonTable("SSN", "firstName", "lastName",
                    "phoneNumber","email", "password", "address");


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
            String updateQuery = "DELETE FROM person WHERE SSN = ?";
            pt = connection.prepareStatement(updateQuery);
            pt.setString(1, deleteTextField.getText());
            pt.executeUpdate();
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("Information removed");
            a.showAndWait();
            table.getItems().clear();
            select();
            deleteTextField.clear();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML public void backButton(ActionEvent ae){

        SceneSwitcher.switchScene(ae,"../View/Admin_menu.fxml");


    }


}

