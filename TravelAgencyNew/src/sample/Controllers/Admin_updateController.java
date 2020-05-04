package sample.Controllers;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Database.PersonQueries;
import sample.Model.PersonTable;
import sample.Model.PersonalInfoException;
import sample.Model.SceneSwitcher;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class Admin_updateController implements Initializable {


    private String url1 = "jdbc:mysql://den1.mysql5.gear.host:3306/travelagency1";
    private String userName1 = "travelagency1";
    private String password1 = "Cw0Mr?!4KN2V";
    private Connection connection;
    private ObservableList<PersonTable> obsList = FXCollections.observableArrayList();
    @FXML
    private TableView<PersonTable> table;

    @FXML
    private TableColumn<PersonTable, String> SSNColumn;

    @FXML
    private TableColumn<PersonTable, String> firstNameColumn;

    @FXML
    private TableColumn<PersonTable, String> lastNameColumn;

    @FXML
    private TableColumn<PersonTable, String> phoNumColumn;

    @FXML
    private TableColumn<PersonTable, String> emailColumn;

    @FXML
    private TableColumn<PersonTable, String> passwordColumn;

    @FXML
    private TableColumn<PersonTable, String> addressColumn;

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


    private PersonalInfoException personalInfoException;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        personalInfoException = new PersonalInfoException();
        personalInfoException.onlyNumber(ssnTxtField);
        personalInfoException.onlyNumber(phoneTxtField);


        textLimiter(phoneTxtField,13);


    }

    @FXML public void select() throws SQLException {
        connection = DriverManager.getConnection(url1,
                userName1, password1);

        String selectQuery = "SELECT * FROM person WHERE SSN != 199205134561";

        ResultSet resultSet = connection.createStatement().executeQuery(selectQuery);
        SSNColumn.setCellValueFactory(new PropertyValueFactory<>("SSN"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phoNumColumn.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
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


    @FXML public void update() throws SQLException {

        connection = DriverManager.getConnection(url1,userName1,password1);
        String updateQuery = "UPDATE person SET phoneNumber = ?, email = ?, password = ?, address = ?  WHERE SSN = ?";
        PreparedStatement pt = connection.prepareStatement(updateQuery);
        pt.setString(1,phoneTxtField.getText());
        pt.setString(2,emailTxtField.getText());
        pt.setString(3,passwordTxtField.getText());
        pt.setString(4,addressTxtField.getText());
        pt.setString(5,ssnTxtField.getText());
        pt.executeUpdate();
        table.getItems().clear();
        select();
        phoneTxtField.clear();
        emailTxtField.clear();
        passwordTxtField.clear();
        addressTxtField.clear();
        ssnTxtField.clear();


    }


    @FXML public void backButton(ActionEvent ae){

        SceneSwitcher.switchScene(ae,"../View/Admin_menu.fxml");

        /*Node node = (Node) ae.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/Admin_menu.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            System.out.println("Hi");
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);*/


    }
    public void textLimiter ( final TextField tf, final int maxLength){
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tf.getText().length() > maxLength&& tf.getText().length()<16) {
                    String s = tf.getText().substring(0, maxLength);
                    tf.setText(s);
                }
            }
        });
    }

  /*  @FXML public void update(){

        Alert a = new Alert(Alert.AlertType.ERROR);

        try{
            PersonQueries personQueries = new PersonQueries();

            boolean allowSave = true;

            if (allowSave) {
                if (personQueries.emailExists(emailTxtField.getText())) {
                    allowSave = false;
                    a.setTitle("Email");
                    a.setHeaderText("Email already exist! Please try again");
                    a.showAndWait();
                }
            }
            if (allowSave) {
                if (personalInfoException.lengthOfPassword(passwordTxtField.getText())&&personalInfoException.lengthOfPassword1(passwordTxtField.getText())) {
                    allowSave = false;
                    a.setTitle("Password");
                    a.setHeaderText("Password should be from 8 to 16 digits or letters");
                    a.showAndWait();
                }
            }
            if (allowSave) {
                if (personalInfoException.IsNotPhoneNumber(phoneTxtField.getText())){
                    allowSave = false;
                    a.setTitle("Phone Number");
                    a.setHeaderText("phone number can be 13 numbers");
                    a.showAndWait();
                }
            }
            if (allowSave){
                personQueries.viewPersonInformation(phoneTxtField.getText(),emailTxtField.getText(),
                        passwordTxtField.getText(),addressTxtField.getText(),ssnTxtField.getText());

                phoneTxtField.clear();
                emailTxtField.clear();
                passwordTxtField.clear();
                addressTxtField.clear();
                ssnTxtField.clear();

                a.setHeaderText("connected!");
                a.showAndWait();
                select();


            }

        }catch (Exception e){

            a.setHeaderText("Error");
            a.setContentText("Could not update");
            a.showAndWait();


        }
    }*/
    }




