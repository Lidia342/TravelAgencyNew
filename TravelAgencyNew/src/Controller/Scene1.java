package Controller;

import Model.Booking;
import Model.Data;
import Model.Flight;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class Scene1 implements Initializable {

    @FXML TextField location;
    @FXML TextField destination;
    @FXML
    DatePicker departureDate;
    @FXML
    DatePicker returnDate;
    @FXML
    ChoiceBox choiceBox;
    @FXML
    Button bookFlight;


    Data myData=Data.getInstance();
    Connection connection = myData.getDatabaseConnection().connect();
    long millis=System.currentTimeMillis();
    Date currentDate = new java.sql.Date(millis);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //choiceBox.setItems();
    }

    @FXML public void createBooking() throws SQLException {


        ResultSet resultSet = myData.getDBConnection().getFlightRow(location.getText(), destination.getText());

        Flight flight = new Flight(resultSet.getInt("flightID"),location.getText(), destination.getText(), resultSet.getDate("flightDepartureDate"), resultSet.getDate("flightArrivalDate"), resultSet.getDouble("flightPrice"));

        myData.getDBConnection().createBooking(flight.getPrice(), (java.sql.Date) currentDate, myData.getUserID(), flight, null, null);
    }
}
