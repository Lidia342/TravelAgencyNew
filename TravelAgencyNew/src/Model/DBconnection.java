package Model;

import com.sun.glass.ui.EventLoop;
import javafx.fxml.Initializable;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DBconnection implements Initializable{

    Connection connection = null;
    Statement statement = null;
    Data myData = Data.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        connection = Data.getInstance().getDatabaseConnection().connect();
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean customerLogin(String email, String password){

        String query = String.format("select * from customers where email="+email+" and password="+password);
        try {
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.getRow()==1){

                myData.setUserID(resultSet.getInt(1));
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean adminLogin(String email, String password){

        String query = String.format("select * from admins where email="+email+" and password="+password);

        try {
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.getRow()==1){

                return true;
            }
            else{
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public Booking createBooking(double totalPrice, Date bookingDate, int customerID, Flight flight, Hotel hotel, Car car) {

        if(hotel==null&&car==null){

            String query = String.format("INSERT INTO booking (bookingTotalPrice, bookingDate, customer_customerID, flight_flightID) " +
                    "VALUES ("+totalPrice+", "+bookingDate+", "+customerID+", "+flight.getFlight_Id());
            try {
                statement.executeQuery(query);
                Booking booking = new Booking(bookingDate, myData.getUserID(), flight.getPrice(), false, flight, null, null);
                return booking;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        else if(car==null){
            String query = String.format("INSERT INTO booking (bookingTotalPrice, bookingDate, hotel_hotelID, customer_customerID, flight_flightID) " +
                    "VALUES ("+totalPrice+", "+bookingDate+", "+hotel.getHotelID()+", "+customerID+", "+flight.getFlight_Id());
            try {
                statement.executeQuery(query);
                Booking booking = new Booking(bookingDate, myData.getUserID(), flight.getPrice(), false, flight, null, null);
                return booking;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            String query = String.format("INSERT INTO booking (bookingTotalPrice, bookingDate, hotel_hotelID, customer_customerID, flight_flightID, car_carID) " +
                    "VALUES ("+totalPrice+", "+bookingDate+", "+hotel.getHotelID()+", "+customerID+", "+flight.getFlight_Id()+", "+car.getCarId());

            try {
                statement.executeQuery(query);
                Booking booking = new Booking(bookingDate, myData.getUserID(), flight.getPrice(), false, flight, null, null);
                return booking;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public ResultSet getFlightRow(String location, String desination){

        String query1 = String.format("SELECT country_Code FROM country WHERE country_name="+location);
        String query2 = String.format("SELECT country_Code FROM country WHERE country_name="+desination);

        try {
            String code1 = String.valueOf(statement.executeQuery(query1));
            String code2 = String.valueOf(statement.executeQuery(query2));

            String query=String.format("SELECT * FROM flight WHERE departing_country_Code="+code1+" AND arriving_country_Code="+code2);
            ResultSet resultSet = statement.executeQuery(query);
            return resultSet;
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("Error occured!");
        }
        return null;
    }
}
