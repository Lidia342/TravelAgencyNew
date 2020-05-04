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

    /*private String url1 = "jdbc:mysql://den1.mysql5.gear.host:3306/travelagency1";
    private String userName1 = "travelagency1";
    private String password1 = "Cw0Mr?!4KN2V";
    private Connection connection;*/
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

UserTable ut;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exception = new Exception();
        exception.onlyNumber(ssnTxtField);
        exception.onlyNumber(phoneTxtField);


        inputLimit(phoneTxtField,13,10);

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
    /*
    public void reloaddata(){

        PersonQueries pq = new PersonQueries();
        DataStorage.getInstance().setPersonData(pq.getPersonInfo());

    }
    /*
    @FXML public void select() throws SQLException {

        // PersonQueries personQueries = new PersonQueries();'






/*        for (int i = 0; i < DataStorage.getInstance().getPersonData().size(); i++) {
            obsList.add(new UserTable(DataStorage.getInstance().getPersonData().get(i).getSSN(),
                    DataStorage.getInstance().getPersonData().get(i).getFirstName(),
                    DataStorage.getInstance().getPersonData().get(i).getLastName(),
                    DataStorage.getInstance().getPersonData().get(i).getPhoneNumber(),
                    DataStorage.getInstance().getPersonData().get(i).getEmail(),
                    DataStorage.getInstance().getPersonData().get(i).getPassword(),
                    DataStorage.getInstance().getPersonData().get(i).getAddress(),
                    DataStorage.getInstance().getPersonData().get(i).getType()));

        }*/





        // table.setItems(obsList);

        /*connection = DriverManager.getConnection(url1,
                userName1, password1);

        String selectQuery = "SELECT * FROM person WHERE SSN != 199205134561";

        ResultSet resultSet = connection.createStatement().executeQuery(selectQuery);

        personQueries.personTableSelect();

        SSNColumn.setCellValueFactory(new PropertyValueFactory<>("SSN"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phoNumColumn.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("Password"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("Address"));

        PersonTable ut = new PersonTable("SSN", "firstName", "lastName",
                "phoneNumber","email", "password", "address");

        ut.set

        /*while (resultSet.next()){
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

        table.setItems(obsList);*/

    //n}


    @FXML public void update() throws SQLException {

        personQueries.updatePersonTable(phoneTxtField.getText(), passwordTxtField.getText() , addressTxtField.getText(),
                emailTxtField.getText(),ssnTxtField.getText());
        table.getItems().clear();
        //select();
        phoneTxtField.clear();
        emailTxtField.clear();
        passwordTxtField.clear();
        addressTxtField.clear();
        ssnTxtField.clear();

        /*connection = DriverManager.getConnection(url1,userName1,password1);
        String updateQuery = "UPDATE person SET phoneNumber = ?, email = ?, password = ?, address = ?  WHERE SSN = ?";
        PreparedStatement pt = connection.prepareStatement(updateQuery);
        pt.setString(1,phoneTxtField.getText());
        pt.setString(2,emailTxtField.getText());
        pt.setString(3,passwordTxtField.getText());
        pt.setString(4,addressTxtField.getText());
        pt.setString(5,ssnTxtField.getText());
        pt.executeUpdate();*/
        table.getItems().clear();
        //select();
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
    public void inputLimit ( final TextField txtFld, final int maxSize, final int minSize){
        txtFld.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (txtFld.getText().length() > maxSize && txtFld.getText().length()< minSize){
                    String str = txtFld.getText().substring(0, maxSize);
                    String str1 = txtFld.getText().substring(0, minSize);
                    txtFld.setText(str);
                    txtFld.setText(str1);
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




