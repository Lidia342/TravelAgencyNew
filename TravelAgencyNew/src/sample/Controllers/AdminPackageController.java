package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Database.PackageQueries;
import sample.Model.AdminPackageTable;
import sample.Model.HandlesException;
import sample.Model.SceneSwitcher;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminPackageController implements Initializable {

    private HandlesException handlesException;
    Alert e = new Alert(Alert.AlertType.ERROR);
    PackageQueries pq = new PackageQueries();

    @FXML
    private TableView<Object> table;

    @FXML
    private TableColumn<AdminPackageTable, String> flightIdColumn;

    @FXML
    private TableColumn<AdminPackageTable, String> packageNameColumn;

    @FXML
    private TableColumn<AdminPackageTable, String> departureDateColumn;

    @FXML
    private TableColumn<AdminPackageTable, String> returnDateColumn;

    @FXML
    private TableColumn<AdminPackageTable, String> departureCityColumn;

    @FXML
    private TableColumn<AdminPackageTable, String> arrivalCityColumn;

    @FXML
    private TableColumn<AdminPackageTable, String> flightNameColumn;

    @FXML
    private TableColumn<AdminPackageTable, String> hotelNameColumn;

    @FXML
    private TableColumn<AdminPackageTable, String> numOfNights;

    @FXML
    private TableColumn<AdminPackageTable, String> typeOfRoomColumn;

    @FXML
    private TableColumn<AdminPackageTable, String> carTypeColumn;

    @FXML
    private TableColumn<AdminPackageTable, String> priceColumn;

    @FXML
    private TextField flightIdTxtField;

    @FXML
    private TextField departureDateTxtField;

    @FXML
    private TextField returnDateTxtField;

    @FXML
    private Button updateButton;

    public void display(){
        flightIdColumn.setCellValueFactory(new PropertyValueFactory<>("FlightID"));
        packageNameColumn.setCellValueFactory(new PropertyValueFactory<>("packageName"));
        departureDateColumn.setCellValueFactory(new PropertyValueFactory<>("departureDate"));
        returnDateColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        departureCityColumn.setCellValueFactory(new PropertyValueFactory<>("departureCity"));
        arrivalCityColumn.setCellValueFactory(new PropertyValueFactory<>("arrivalCity"));
        flightNameColumn.setCellValueFactory(new PropertyValueFactory<>("flightName"));
        hotelNameColumn.setCellValueFactory(new PropertyValueFactory<>("hotelName"));
        numOfNights.setCellValueFactory(new PropertyValueFactory<>("numOfNights"));
        typeOfRoomColumn.setCellValueFactory(new PropertyValueFactory<>("typeOfRoom"));
        carTypeColumn.setCellValueFactory(new PropertyValueFactory<>("carType"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        pq.adminPackage();
        table.setItems(pq.getOList());
    }
    public void update(){

        pq.updateDate(departureDateTxtField.getText(), returnDateTxtField.getText(),flightIdTxtField.getText());
        table.getItems().clear();
        display();
        departureDateTxtField.clear();
        returnDateTxtField.clear();
        flightIdTxtField.clear();

        /*String dateTime = departureDateTxtField.getText();
        String dateTime1 = returnDateTxtField.getText();

        java.util.Date date = new java.util.Date();
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);

        try{
            if (!dateTime.equals(time) ||  !dateTime1.equals(time)) {
                e.setTitle("Invalid Input!");
                e.setHeaderText(" check your SSN or date format please");
                e.show();
            } else {
                pq.updateDate(departureDateTxtField.getText(), returnDateTxtField.getText(),flightIdTxtField.getText());
                table.getItems().clear();
                display();
                departureDateTxtField.clear();
                returnDateTxtField.clear();
                flightIdTxtField.clear();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }*/


    }







    @FXML public void backButton(ActionEvent ae){
        SceneSwitcher.SwitchScene(ae,"../View/AdminMenu.fxml");

    }
    @FXML public void cancel(){
        System.exit(0);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Tooltip idTool = new Tooltip();
        idTool.setText("Enter the id of the flight as you see from the table");
        flightIdTxtField.setTooltip(idTool);

        Tooltip departureToool = new Tooltip();
        departureToool.setText("Enter the departure date");
        departureDateTxtField.setTooltip(departureToool);

        Tooltip returnTool = new Tooltip();
        returnTool.setText("Enter the return date");
        returnDateTxtField.setTooltip(returnTool);

        Tooltip updateTool = new Tooltip();
        updateTool.setText("Press this button to update");
        updateButton.setTooltip(updateTool);


        display();
        handlesException= new HandlesException();
        handlesException.emptyTextFields1(flightIdTxtField,departureDateTxtField,returnDateTxtField,updateButton);
        table.getSelectionModel().selectFirst();

    }
}

