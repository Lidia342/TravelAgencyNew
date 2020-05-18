package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Database.BookingQueries;
import sample.Database.PackageQueries;
import sample.Database.PersonQueries;
import sample.Model.HandlesException;
import sample.Model.CustomerPackageTable;
import sample.Model.SceneSwitcher;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ViewPackageController implements Initializable {

    private HandlesException handlesException;
    Alert e = new Alert(Alert.AlertType.ERROR);

    @FXML
    private RadioButton rb1,rb2,rb3,rb4,rb5,rb6,rb7,rb8,rb9,rb10;

    @FXML
    private ToggleGroup RadioGroup;

    @FXML
    private Label ssnLabel, bookingDateLabel;


    @FXML
    private TableView<Object> packageTable1;

    @FXML
    private TableColumn<CustomerPackageTable, String> packageName;

    @FXML
    private TableColumn<CustomerPackageTable, String> depDate;

    @FXML
    private TableColumn<CustomerPackageTable, String> depCity;

    @FXML
    private TableColumn<CustomerPackageTable, String> arrivalCity;

    @FXML
    private TableColumn<CustomerPackageTable, String> flightName;

    @FXML
    private TableColumn<CustomerPackageTable, String> hotelName;

    @FXML
    private TableColumn<CustomerPackageTable, String> nights;

    @FXML
    private TableColumn<CustomerPackageTable, String> roomType;

    @FXML
    private TableColumn<CustomerPackageTable, String> carType;

    @FXML
    private TableColumn<CustomerPackageTable, String> Price;

    @FXML
    private TableColumn<CustomerPackageTable, String> returnDate;

    @FXML private TextField ssnTxtField;
    @FXML private TextField bookDateTxtField;
    @FXML private Button bookingButton;
    @FXML private Button press;
    @FXML private Label typeIn;


    PackageQueries pq = new PackageQueries();
    PersonQueries personQueries = new PersonQueries();

    public void setInvisible(){
        ssnTxtField.setVisible(false);
        bookDateTxtField.setVisible(false);
        ssnLabel.setVisible(false);
        bookingDateLabel.setVisible(false);
        typeIn.setVisible(false);

    }

    public void setVisible(){
        ssnTxtField.setVisible(true);
        bookDateTxtField.setVisible(true);
        ssnLabel.setVisible(true);
        bookingDateLabel.setVisible(true);
        typeIn.setVisible(true);

    }
    @FXML public void radioSelect1(ActionEvent ae) throws SQLException {


        packageName.setCellValueFactory(new PropertyValueFactory<>("packageName"));
        depDate.setCellValueFactory(new PropertyValueFactory<>("departureDate"));
        returnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        depCity.setCellValueFactory(new PropertyValueFactory<>("departureCity"));
        arrivalCity.setCellValueFactory(new PropertyValueFactory<>("arrivalCity"));
        flightName.setCellValueFactory(new PropertyValueFactory<>("flightName"));
        hotelName.setCellValueFactory(new PropertyValueFactory<>("hotelName"));
        nights.setCellValueFactory(new PropertyValueFactory<>("numOfNights"));
        roomType.setCellValueFactory(new PropertyValueFactory<>("typeOfRoom"));
        carType.setCellValueFactory(new PropertyValueFactory<>("carType"));
        Price.setCellValueFactory(new PropertyValueFactory<>("price"));

        if (rb1.isSelected()) {
            packageTable1.getItems().clear();
            pq.getPackageInfo("Honey Moon");
            packageTable1.setItems(pq.getObList());
        }
        if (rb2.isSelected()) {
            packageTable1.getItems().clear();
            pq.getPackageInfo("Family Trip");
            packageTable1.setItems(pq.getObList());
        }
        if (rb3.isSelected()) {
            packageTable1.getItems().clear();
            pq.getPackageInfo("Barcelona Trip");
            packageTable1.setItems(pq.getObList());

        }
        if (rb4.isSelected()) {
            packageTable1.getItems().clear();
            pq.getPackageInfo("Berlin Trip");
            packageTable1.setItems(pq.getObList());

        }
        if (rb5.isSelected()) {
            packageTable1.getItems().clear();
            pq.getPackageInfo("Amsterdam Trip");
            packageTable1.setItems(pq.getObList());

        }
        if (rb6.isSelected()) {
            packageTable1.getItems().clear();
            pq.getPackageInfo("Sahara Trip");
            packageTable1.setItems(pq.getObList());

        }
        if (rb7.isSelected()) {
            packageTable1.getItems().clear();
            pq.getPackageInfo("Azmarino Trip");
            packageTable1.setItems(pq.getObList());

        }
        if (rb8.isSelected()) {
            packageTable1.getItems().clear();
            pq.getPackageInfo("Campya Trip");
            packageTable1.setItems(pq.getObList());

        }
        if (rb9.isSelected()) {
            packageTable1.getItems().clear();
            pq.getPackageInfo("Shabi Trip");
            packageTable1.setItems(pq.getObList());

        }
        if (rb10.isSelected()) {
            packageTable1.getItems().clear();
            pq.getPackageInfo("Massawa Trip");
            packageTable1.setItems(pq.getObList());
        }


    }
    public void RadioButtonsDisabled(){
        rb1.setDisable(true);
        rb2.setDisable(true);
        rb3.setDisable(true);
        rb4.setDisable(true);
        rb5.setDisable(true);
        rb6.setDisable(true);
        rb7.setDisable(true);
        rb8.setDisable(true);
        rb9.setDisable(true);
        rb10.setDisable(true);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        handlesException = new HandlesException();
        handlesException.onlyNumber(ssnTxtField);
        handlesException.inputLimit(ssnTxtField,12);

        handlesException.emptyTextFields(ssnTxtField,bookDateTxtField,bookingButton);


        setInvisible();

        press.setOnMousePressed(mouseEvent -> {
            if(press.isPressed()){
                RadioButtonsDisabled();
                setVisible();
                press.setDisable(true);

            }
        });


    }

    public void saveToTable(){

        BookingQueries bq = new BookingQueries();

        if (rb1.isSelected()) {
            bq.insertBookedPackageIntoBookingTable(bookDateTxtField.getText(), ssnTxtField.getText(),1);

        }if (rb2.isSelected()) {
            bq.insertBookedPackageIntoBookingTable(bookDateTxtField.getText(), ssnTxtField.getText(),2);

        }if (rb3.isSelected()) {
            bq.insertBookedPackageIntoBookingTable(bookDateTxtField.getText(), ssnTxtField.getText(),3);

        }if (rb4.isSelected()) {
            bq.insertBookedPackageIntoBookingTable(bookDateTxtField.getText(), ssnTxtField.getText(),4);

        }if (rb5.isSelected()) {
            bq.insertBookedPackageIntoBookingTable(bookDateTxtField.getText(), ssnTxtField.getText(),5);

        }if (rb6.isSelected()) {
            bq.insertBookedPackageIntoBookingTable(bookDateTxtField.getText(), ssnTxtField.getText(),6);

        }if (rb7.isSelected()) {
            bq.insertBookedPackageIntoBookingTable(bookDateTxtField.getText(), ssnTxtField.getText(),7);

        }if (rb8.isSelected()) {
            bq.insertBookedPackageIntoBookingTable(bookDateTxtField.getText(), ssnTxtField.getText(),8);

        }if (rb9.isSelected()) {
            bq.insertBookedPackageIntoBookingTable(bookDateTxtField.getText(), ssnTxtField.getText(),9);

        }if (rb10.isSelected()) {
            bq.insertBookedPackageIntoBookingTable(bookDateTxtField.getText(), ssnTxtField.getText(),10);

        }

    }

  @FXML  public void confirmBooking() {

      String currTime = bookDateTxtField.getText();

      java.util.Date date = new java.util.Date();
      java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
      String time = format.format(date);



          if (personQueries.userNotExists(ssnTxtField.getText()) ||  !currTime.equals(time)) {
              e.setTitle("Invalid Input!");
              e.setHeaderText(" check your SSN or date format please");
              e.show();
          } else {
              saveToTable();
              alertSuccess();
              ssnTxtField.clear();
              bookDateTxtField.clear();
          }

      }

    public void alertSuccess(){
        Alert success = new Alert(Alert.AlertType.CONFIRMATION);
        success.setTitle("Booking Information");
        success.setHeaderText("you have made your booking Successfully! Thank you");
        success.show();
    }
    @FXML public void back(ActionEvent ae) throws IOException {
        SceneSwitcher.SwitchScene(ae,"../View/CustomerMenu.fxml");

    }
    @FXML public void cancel(){
        System.exit(0);
    }

}

