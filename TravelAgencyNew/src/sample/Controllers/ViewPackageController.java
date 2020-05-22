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
    private Label ssnLabel;


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
    @FXML private Button bookingButton;
    @FXML private Button press;
    @FXML private Label typeIn;



    PackageQueries pq = new PackageQueries();
    PersonQueries personQueries = new PersonQueries();

    public void setInvisible(){
        ssnTxtField.setVisible(false);
        ssnLabel.setVisible(false);
        typeIn.setVisible(false);

    }

    public void setVisible(){
        ssnTxtField.setVisible(true);
        ssnLabel.setVisible(true);
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
            pq.getPackageInfo("Amsterdam Trip");
            packageTable1.setItems(pq.getObList());
        }
        if (rb2.isSelected()) {
            packageTable1.getItems().clear();
            pq.getPackageInfo("Rome Trip");
            packageTable1.setItems(pq.getObList());
        }
        if (rb3.isSelected()) {
            packageTable1.getItems().clear();
            pq.getPackageInfo("Barcelona Trip");
            packageTable1.setItems(pq.getObList());

        }
        if (rb4.isSelected()) {
            packageTable1.getItems().clear();
            pq.getPackageInfo("Los Angeles Trip");
            packageTable1.setItems(pq.getObList());

        }
        if (rb5.isSelected()) {
            packageTable1.getItems().clear();
            pq.getPackageInfo("Rio Trip");
            packageTable1.setItems(pq.getObList());

        }
        if (rb6.isSelected()) {
            packageTable1.getItems().clear();
            pq.getPackageInfo("Maimi Trip");
            packageTable1.setItems(pq.getObList());

        }
        if (rb7.isSelected()) {
            packageTable1.getItems().clear();
            pq.getPackageInfo("Launda Trip");
            packageTable1.setItems(pq.getObList());

        }
        if (rb8.isSelected()) {
            packageTable1.getItems().clear();
            pq.getPackageInfo("Kampala Trip");
            packageTable1.setItems(pq.getObList());

        }
        if (rb9.isSelected()) {
            packageTable1.getItems().clear();
            pq.getPackageInfo("Khartoum Trip");
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

        Tooltip radioTool = new Tooltip();
        radioTool.setText("press this package to view the details");
        rb1.setTooltip(radioTool); rb2.setTooltip(radioTool);
        rb3.setTooltip(radioTool); rb4.setTooltip(radioTool);
        rb5.setTooltip(radioTool); rb6.setTooltip(radioTool);
        rb7.setTooltip(radioTool); rb8.setTooltip(radioTool);
        rb9.setTooltip(radioTool); rb10.setTooltip(radioTool);

        Tooltip pressTool = new Tooltip();
        pressTool.setText("press this button to book the selected package");
        press.setTooltip(pressTool);

        Tooltip bookingTool = new Tooltip();
        bookingTool.setText("press this button to confirm the booking");
        bookingButton.setTooltip(bookingTool);


        Tooltip ssnTool = new Tooltip();
        ssnTool.setText("enter your social security number here, it shall be 12 numbers");
        ssnTxtField.setTooltip(ssnTool);


        handlesException = new HandlesException();
        handlesException.onlyNumber(ssnTxtField);
        handlesException.inputLimit(ssnTxtField,12);

        handlesException.emptyTextFields(ssnTxtField,bookingButton);


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

        java.util.Date date = new java.util.Date();
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(date);

        if (rb1.isSelected()) {
            bq.insertBookedPackageIntoBookingTable(time, ssnTxtField.getText());
            bq.insertBookedIntoBookingHasPackageTable(1, bq.getBookingId());

        }if (rb2.isSelected()) {
            bq.insertBookedPackageIntoBookingTable(time, ssnTxtField.getText());
            bq.insertBookedIntoBookingHasPackageTable(2, bq.getBookingId());

        }if (rb3.isSelected()) {
            bq.insertBookedPackageIntoBookingTable(time, ssnTxtField.getText());
            bq.insertBookedIntoBookingHasPackageTable(3, bq.getBookingId());

        }if (rb4.isSelected()) {
            bq.insertBookedPackageIntoBookingTable(time, ssnTxtField.getText());
            bq.insertBookedIntoBookingHasPackageTable(4, bq.getBookingId());

        }if (rb5.isSelected()) {
            bq.insertBookedPackageIntoBookingTable(time, ssnTxtField.getText());
            bq.insertBookedIntoBookingHasPackageTable(5, bq.getBookingId());

        }if (rb6.isSelected()) {
            bq.insertBookedPackageIntoBookingTable(time, ssnTxtField.getText());
            bq.insertBookedIntoBookingHasPackageTable(6, bq.getBookingId());

        }if (rb7.isSelected()) {
            bq.insertBookedPackageIntoBookingTable(time, ssnTxtField.getText());
            bq.insertBookedIntoBookingHasPackageTable(7, bq.getBookingId());

        }if (rb8.isSelected()) {
            bq.insertBookedPackageIntoBookingTable(time, ssnTxtField.getText());
            bq.insertBookedIntoBookingHasPackageTable(8, bq.getBookingId());

        }if (rb9.isSelected()) {
            bq.insertBookedPackageIntoBookingTable(time, ssnTxtField.getText());
            bq.insertBookedIntoBookingHasPackageTable(9, bq.getBookingId());

        }if (rb10.isSelected()) {
            bq.insertBookedPackageIntoBookingTable(time, ssnTxtField.getText());
            bq.insertBookedIntoBookingHasPackageTable(10, bq.getBookingId());

        }

    }

  @FXML  public void confirmBooking(ActionEvent ae) {

      //String currTime = bookDateTxtField.getText();





          if (personQueries.userNotExists(ssnTxtField.getText())) {
              e.setTitle("Invalid Input!");
              e.setHeaderText(" check your SSN");
              e.show();
          } else {
              saveToTable();
              alertSuccess();
              ssnTxtField.clear();
              SceneSwitcher.SwitchScene(ae, "../View/CustomerMenu.fxml");
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