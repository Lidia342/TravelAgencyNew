package sample.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import sample.Model.Package;
import sample.Model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;


public class BookingQueries extends DatabaseConnection {


    private ObservableList<Object> obList = FXCollections.observableArrayList();
    private ObservableList<Object> viewList = FXCollections.observableArrayList();

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    Data myData = Data.getInstance();
    static HashSet packageList = new HashSet<>();
    private String name1;
    private ArrayList<BookingPdf> cusBookingInfo = new ArrayList<>();

    public BookingQueries() {
        try{
            this.connection = getConnection();
            this.statement = connection.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Package getPackage() {

        return null;
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


            //Flight flight = new Flight(Integer.parseInt(resultSet4.getString(1)), departureDate, location, destination, 80.0);
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
            return null;
    }

    public HashSet<Package> getPackagesList(){

        return packageList;
    }

    public void deletePackage() {
    }

    //public Package getPackage





    public void insertBookedPackageIntoBookingTable(String date, String user_SSN){

        String BookingQuery = "INSERT INTO booking (date, user_SSN) " +
                       "VALUES (?, ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(BookingQuery)){
            preparedStatement.setString(1, date);
            preparedStatement.setString(2, user_SSN);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());        }
    }
    public void insertBookedIntoBookingHasPackageTable(int package_packageId, int booking_bookingId){


        String sQuery = "INSERT INTO booking_has_package (booking_bookingID, package_packageId)" +
                "VALUES(?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sQuery)){
            preparedStatement.setInt(1,booking_bookingId);
            preparedStatement.setInt(2,package_packageId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());        }
    }
    public void displayBooked(){
        try{
            String selectQuery = "select b.bookingID, u.SSN, u.firstName, u.lastName, p.packageName, b.date, p.price from booking b " +
                    "join booking_has_package bhp On bhp.booking_bookingID = b.bookingID join package p " +
                    "On bhp.package_packageId = p.packageId join user u  on b.user_SSN = u.SSN";

            ResultSet resultSet = connection.createStatement().executeQuery(selectQuery);
            while (resultSet.next()) {
                BookingTable bookingTable = new BookingTable("bookingID","SSN","firstName","lastName",
                        "packageName","date","price");
                bookingTable.setBookingId(resultSet.getString("bookingID"));
                bookingTable.setSSN(resultSet.getString("SSN"));
                bookingTable.setFirstName(resultSet.getString("firstName"));
                bookingTable.setLastName(resultSet.getString("lastName"));
                bookingTable.setPackageName(resultSet.getString("packageName"));
                bookingTable.setDate(resultSet.getString("date"));
                bookingTable.setPrice(resultSet.getString("price"));

                viewList.add(bookingTable);

            }
            setViewList(viewList);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void viewBooked(String name){
        try{
            String selectQuery = "select b.bookingID, u.SSN, u.firstName, u.lastName, p.packageName, b.date, p.price from booking b " +
                    "join booking_has_package bhp On bhp.booking_bookingID = b.bookingID join package p " +
                    "On bhp.package_packageId = p.packageId join user u  on b.user_SSN = u.SSN where p.packageName =  " + "\'" + name + "\'";

            ResultSet resultSet = connection.createStatement().executeQuery(selectQuery);
            while (resultSet.next()) {
                BookingTable bookingTable = new BookingTable("bookingID","SSN","firstName","lastName",
                        "packageName","date","price");
                bookingTable.setBookingId(resultSet.getString("bookingID"));
                bookingTable.setSSN(resultSet.getString("SSN"));
                bookingTable.setFirstName(resultSet.getString("firstName"));
                bookingTable.setLastName(resultSet.getString("lastName"));
                bookingTable.setPackageName(resultSet.getString("packageName"));
                bookingTable.setDate(resultSet.getString("date"));
                bookingTable.setPrice(resultSet.getString("price"));

                obList.add(bookingTable);

            }
            setObList(obList);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void customerBookingInfo(int id){
        try {
            String selQueries = " select b.bookingID,  CONCAT(firstName, ' ', lastName) AS Name,  p.packageName, b.date bookingDate, p.price, " +
                    "f.departureDate, f.returnDate, f.departureCity, f.arrivalCity, f.flightName, h.hotelName, h.numOfNights," +
                    " h.typeOfRoom, c.carType from booking b join booking_has_package bhp On bhp.booking_bookingID = b.bookingID " +
                    "join package p On bhp.package_packageId = p.packageId Join flight f On p.flight_flightID = f.flightID " +
                    "join hotel h on p.hotel_hotelID = h.hotelID join car c on p.car_carID =  c.carID " +
                    "join user u  on b.user_SSN = u.SSN where bookingID = " + "\'" + id + "\'";


            //resultSet = statement.executeQuery(selQueries);
            resultSet = connection.createStatement().executeQuery(selQueries);
            while (resultSet.next()) {
                BookingPdf bookingpdf = new BookingPdf("",  "", "", "", "", "",
                        "", "", "", "", "", "", "", "");
                bookingpdf.setBookingID(resultSet.getString("bookingId"));
                bookingpdf.setName(resultSet.getString("Name"));
                bookingpdf.setPackageName(resultSet.getString("packageName"));
                bookingpdf.setBookingDate(resultSet.getString("bookingDate"));
                bookingpdf.setPrice(resultSet.getString("price"));
                bookingpdf.setDepartureDate(resultSet.getString("departureDate"));
                bookingpdf.setReturnDate(resultSet.getString("returnDate"));
                bookingpdf.setDepartureCity(resultSet.getString("departureCity"));
                bookingpdf.setArrivalCity(resultSet.getString("arrivalCity"));
                bookingpdf.setFlightName(resultSet.getString("flightName"));
                bookingpdf.setHotelName(resultSet.getString("hotelName"));
                bookingpdf.setNumOfNights(resultSet.getString("numOfNights"));
                bookingpdf.setTypeOfRoom(resultSet.getString("typeOfRoom"));
                bookingpdf.setCarType(resultSet.getString("carType"));

                cusBookingInfo.add(bookingpdf);
            }
            setCusBookingInfo(cusBookingInfo);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeBooking(String ssn){
        String removeQuery = "DELETE From booking WHERE bookingID = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(removeQuery);

            preparedStatement.setString(1,ssn);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void removeBookingHasPackage(String  id){
        String deleteQuery= "DELETE From booking_has_package WHERE booking_bookingID = ?";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);

            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public int getBookingId(){
        int lastBookingId = -1;
        try{
            resultSet= statement.executeQuery("Select Max(BookingID) from booking");

            while (resultSet.next()){
                lastBookingId = resultSet.getInt(1);

            }
            return lastBookingId;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public ObservableList<Object> getObList() {
        return obList;
    }

    public void setObList(ObservableList<Object> obList) {
        this.obList = obList;
    }

    public ObservableList<Object> getViewList() {
        return viewList;
    }

    public void setViewList(ObservableList<Object> viewList) {
        this.viewList = viewList;
    }

    public ArrayList<BookingPdf> getCusBookingInfo() {
        return cusBookingInfo;
    }

    public void setCusBookingInfo(ArrayList<BookingPdf> cusBookingInfo) {
        this.cusBookingInfo = cusBookingInfo;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }
}
