package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.Database.BookingQueries;
import sample.Database.PackageQueries;
import sample.Database.PersonQueries;
import sample.Model.Data;
import sample.Model.HandlesException;
import sample.Model.SceneSwitcher;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ViewPackageController implements Initializable {

    private HandlesException handlesException;
    private Data myData= Data.getInstance();
    Alert e = new Alert(Alert.AlertType.ERROR);

    @FXML
    private RadioButton rb1,rb2,rb3,rb4,rb5,rb6,rb7,rb8,rb9,rb10;

    @FXML
    private ToggleGroup RadioGroup;

    @FXML
    private Label ssnLabel;



    @FXML
    private TextField departureDate;

    @FXML
    private TextField returnDate;

    @FXML
    private TextField departingCity;

    @FXML
    private TextField arrivalCity;

    @FXML
    private TextField flightName;

    @FXML
    private TextField hotelName;

    @FXML
    private TextField roomType;

    @FXML
    private TextField nights;

    @FXML
    private TextField carType;

    @FXML
    private TextField packageName;

    @FXML
    private TextField price;

    @FXML private TextField ssnTxtField;
    @FXML private Button bookingButton;
    @FXML private Button press;


    PackageQueries pq = new PackageQueries();
    PersonQueries personQueries = new PersonQueries();

    public void setInvisible(){
        ssnTxtField.setVisible(false);
        ssnLabel.setVisible(false);


    }

    public void setVisible(){
        ssnTxtField.setVisible(true);
        ssnLabel.setVisible(true);

    }

    public void setFields(){

        for (int i = 0; i < pq.getList().size() ; i++) {
            packageName.setText(String.valueOf(pq.getList().get(i).getPackageName()));
            departureDate.setText(String.valueOf(pq.getList().get(i).getDepartureDate()));
            returnDate.setText(String.valueOf(pq.getList().get(i).getReturnDate()));
            departingCity.setText(String.valueOf(pq.getList().get(i).getDepartureCity()));
            arrivalCity.setText(String.valueOf(pq.getList().get(i).getArrivalCity()));
            flightName.setText(String.valueOf(pq.getList().get(i).getFlightName()));
            hotelName.setText(String.valueOf(pq.getList().get(i).getHotelName()));
            roomType.setText(String.valueOf(pq.getList().get(i).getRoomType()));
            nights.setText(String.valueOf(pq.getList().get(i).getNights()));
            carType.setText(String.valueOf(pq.getList().get(i).getCarType()));
            price.setText(String.valueOf(pq.getList().get(i).getPrice()));
        }

    }

    @FXML public void radioSelect1(ActionEvent ae) throws SQLException {


        if (rb1.isSelected()) {
           pq.viewPackage("Amsterdam Trip");
          setFields();

        }
        if (rb2.isSelected()) {
            pq.viewPackage("Rome Trip");
            setFields();
        }
        if (rb3.isSelected()) {
            pq.viewPackage("Barcelona Trip");
           setFields();
        }
        if (rb4.isSelected()) {
            pq.viewPackage("Los Angeles Trip");
            setFields();
        }
        if (rb5.isSelected()) {
            pq.viewPackage("Rio Trip");
            setFields();

        }
        if (rb6.isSelected()) {
            pq.viewPackage("Maimi Trip");
            setFields();
        }
        if (rb7.isSelected()) {
            pq.viewPackage("Launda Trip");
            setFields();

        }
        if (rb8.isSelected()) {
            pq.viewPackage("Kampala Trip");
            setFields();
        }
        if (rb9.isSelected()) {
            pq.viewPackage("Khartoum Trip");
            setFields();
        }
        if (rb10.isSelected()) {
            pq.viewPackage("Massawa Trip");
            setFields();
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

        packageName.setEditable(false);
        departureDate.setEditable(false);
        returnDate.setEditable(false);
        departingCity.setEditable(false);
        arrivalCity.setEditable(false);
        flightName.setEditable(false);
        hotelName.setEditable(false);
        nights.setEditable(false);
        roomType.setEditable(false);
        carType.setEditable(false);
        price.setEditable(false);


    }

    public void saveToTable() throws SQLException {

        BookingQueries bq = new BookingQueries();

        java.util.Date date = new java.util.Date();
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(date);

        if (rb1.isSelected()) {
            bq.insertBookedPackageIntoBookingTable(time, ssnTxtField.getText());
            bq.insertBookedIntoBookingHasPackageTable(1, bq.getBookingId());
            myData.setCurrentBooking(bq.retreiveBooking(myData.getUser().getSSN()));

        }if (rb2.isSelected()) {
            bq.insertBookedPackageIntoBookingTable(time, ssnTxtField.getText());
            bq.insertBookedIntoBookingHasPackageTable(2, bq.getBookingId());
            myData.setCurrentBooking(bq.retreiveBooking(myData.getUser().getSSN()));

        }if (rb3.isSelected()) {
            bq.insertBookedPackageIntoBookingTable(time, ssnTxtField.getText());
            bq.insertBookedIntoBookingHasPackageTable(3, bq.getBookingId());
            myData.setCurrentBooking(bq.retreiveBooking(myData.getUser().getSSN()));

        }if (rb4.isSelected()) {
            bq.insertBookedPackageIntoBookingTable(time, ssnTxtField.getText());
            bq.insertBookedIntoBookingHasPackageTable(4, bq.getBookingId());
            myData.setCurrentBooking(bq.retreiveBooking(myData.getUser().getSSN()));

        }if (rb5.isSelected()) {
            bq.insertBookedPackageIntoBookingTable(time, ssnTxtField.getText());
            bq.insertBookedIntoBookingHasPackageTable(5, bq.getBookingId());
            myData.setCurrentBooking(bq.retreiveBooking(myData.getUser().getSSN()));

        }if (rb6.isSelected()) {
            bq.insertBookedPackageIntoBookingTable(time, ssnTxtField.getText());
            bq.insertBookedIntoBookingHasPackageTable(6, bq.getBookingId());
            myData.setCurrentBooking(bq.retreiveBooking(myData.getUser().getSSN()));

        }if (rb7.isSelected()) {
            bq.insertBookedPackageIntoBookingTable(time, ssnTxtField.getText());
            bq.insertBookedIntoBookingHasPackageTable(7, bq.getBookingId());
            myData.setCurrentBooking(bq.retreiveBooking(myData.getUser().getSSN()));

        }if (rb8.isSelected()) {
            bq.insertBookedPackageIntoBookingTable(time, ssnTxtField.getText());
            bq.insertBookedIntoBookingHasPackageTable(8, bq.getBookingId());
            myData.setCurrentBooking(bq.retreiveBooking(myData.getUser().getSSN()));

        }if (rb9.isSelected()) {
            bq.insertBookedPackageIntoBookingTable(time, ssnTxtField.getText());
            bq.insertBookedIntoBookingHasPackageTable(9, bq.getBookingId());
            myData.setCurrentBooking(bq.retreiveBooking(myData.getUser().getSSN()));

        }if (rb10.isSelected()) {
            bq.insertBookedPackageIntoBookingTable(time, ssnTxtField.getText());
            bq.insertBookedIntoBookingHasPackageTable(10, bq.getBookingId());
            myData.setCurrentBooking(bq.retreiveBooking(myData.getUser().getSSN()));

        }

    }

    @FXML  public void confirmBooking(ActionEvent ae) throws SQLException {

        if (personQueries.userNotExists(ssnTxtField.getText())) {
            e.setTitle("Invalid Input!");
            e.setHeaderText(" check your SSN");
            e.show();
        } else {
            saveToTable();
            alertSuccess();
          //  ssnTxtField.clear();
           SceneSwitcher.SwitchScene(ae, "../View/ViewBookingCustomer.fxml");
        }
    }


        public void alertSuccess () {
            Alert success = new Alert(Alert.AlertType.CONFIRMATION);
            success.setTitle("Booking Information");
            success.setHeaderText("you have made your booking Successfully! Thank you");
            success.show();
        }
        @FXML
        public void back (ActionEvent ae) throws IOException {
            SceneSwitcher.SwitchScene(ae,"../View/CustomerMenu.fxml");

        }
        @FXML public void cancel () {
            System.exit(0);
        }


    }

