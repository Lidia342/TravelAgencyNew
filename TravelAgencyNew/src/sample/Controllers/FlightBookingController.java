package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import sample.Database.BookingQueries;
import sample.Model.Data;
import sample.Model.SceneSwitcher;

import java.net.URL;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class FlightBookingController implements Initializable {

    @FXML
    DatePicker departureDate;

    @FXML
    TextField origin;
    @FXML
    TextField destination;

    Data myData = Data.getInstance();

    BookingQueries bookingQueries;
    {
        bookingQueries = new BookingQueries();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void createBooking() {

        Instant instant = Instant.from(departureDate.getValue().atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);

        //Flight flight = bookingQueries.createFlight(date, origin.getText(), destination.getText(), 80.0);

        long millis=System.currentTimeMillis();
        java.sql.Date date1=new java.sql.Date(millis);
        //bookingQueries.createBooking(80, date1, myData.getUser().getSSN(), flight, null, null);
    }

    @FXML public void continueBooking(ActionEvent ae){

        SceneSwitcher.SwitchScene(ae, "../View/hotel_selection_scene.fxml");
    }
}
