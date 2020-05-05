package sample.Database;

import javafx.scene.control.Alert;
import sample.Model.*;

import java.lang.Exception;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class BookingQueries extends DatabaseConnection {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    Data myData = Data.getInstance();

    public BookingQueries() throws SQLException {
        this.connection = getConnection();
        this.statement = connection.createStatement();
    }

    public Booking createBooking(double totalPrice, Date bookingDate, String customerID, Flight flight, Hotel hotel, Car car) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Booking completed successfully");

        if (hotel == null && car == null) {

            String query = String.format("INSERT INTO booking (bookingTotalPrice, bookingDate, customer_customerID, flight_flightID) " +
                    "VALUES ('" + totalPrice + "', '" + bookingDate + "', '" + hotel.getHotelID() + "', '" + customerID + "', '" + flight.getFlightID() + "')");
            try {
                statement.executeQuery(query);
                Booking booking = new Booking(bookingDate, myData.getUser().getSSN(), false, flight.getPrice(), flight, null, null);
                alert.show();
                return booking;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (car == null) {
            String query = String.format("INSERT INTO booking (bookingTotalPrice, bookingDate, hotel_hotelID, customer_customerID, flight_flightID) " +
                    "VALUES ('" + totalPrice + "', '" + bookingDate + "', '" + hotel.getHotelID() + "', '" + customerID + "', '" + flight.getFlightID() + "', '" + hotel.getHotelID() + "')");
            try {
                statement.executeQuery(query);
                Booking booking = new Booking(bookingDate, myData.getUser().getSSN(), false, flight.getPrice(), flight, hotel, null);
                alert.show();
                return booking;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            String query = String.format("INSERT INTO booking (bookingTotalPrice, bookingDate, hotel_hotelID, customer_customerID, flight_flightID, hotel_hotelID, car_carID) " +
                    "VALUES ('" + totalPrice + "', '" + bookingDate + "', '" + hotel.getHotelID() + "', '" + customerID + "', '" + flight.getFlightID() + "', '" + hotel.getHotelID() + "', '" + car.getCarId() + "')");

            try {
                statement.executeQuery(query);
                Booking booking = new Booking(bookingDate, myData.getUser().getSSN(), false, flight.getPrice(), flight, hotel, car);
                alert.show();
                return booking;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Flight createFlight(Date departureDate, String location, String destination, double price) {

        /*
        String query1 = String.format("SELECT country_Code FROM country WHERE country_name='" + location + "'");
        String query2 = String.format("SELECT country_Code FROM country WHERE country_name='" + destination + "'");
         */

        ArrayList<String> paramters = new ArrayList<>();

        try {

            /*
            String select = "SELECT country_Code FROM country WHERE country_name = ?";

            PreparedStatement preparedStatement1 = connection.prepareStatement(select);
            PreparedStatement preparedStatement2 = connection.prepareStatement(select);

            preparedStatement1.setString(1, location);
            preparedStatement2.setString(1, destination);


            ResultSet resultSet1 = preparedStatement1.executeQuery();
            ResultSet resultSet2 = preparedStatement2.executeQuery();

            resultSet1.first();
            resultSet2.first();

            System.out.println(resultSet1.getString(1));
            System.out.println(resultSet2.getString(1));


             */
            String query = "INSERT INTO flight (flightDepartueDate, flightPrice, departing_country, arriving_country)" +
                    " VALUES (?, ? , ?, ?)";
            PreparedStatement preparedStatement3 = connection.prepareStatement(query);
            preparedStatement3.setDate(1, (java.sql.Date) departureDate);
            preparedStatement3.setDouble(2, 80.0);
            preparedStatement3.setString(3, location);
            preparedStatement3.setString(4, destination);
            preparedStatement3.executeQuery();

            query = "SELECT MAX(flightID) FROM flight";

            PreparedStatement preparedStatement4 = connection.prepareStatement(query);
            ResultSet resultSet4 = preparedStatement4.executeQuery();
            resultSet4.first();


            Flight flight = new Flight(Integer.parseInt(resultSet4.getString(1)), departureDate, location, destination, 80.0);
            return flight;
        } catch (SQLException e) {
            e.printStackTrace();
        }
            return null;
    }
}
