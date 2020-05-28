package sample.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.Booking;
import sample.Model.BookingTable;

import java.sql.*;
import java.util.ArrayList;

public class BookingQueries extends DatabaseConnection {

    private ObservableList<Object> obList = FXCollections.observableArrayList();
    private ObservableList<Object> viewList = FXCollections.observableArrayList();

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    private String name1;
    private ArrayList<Booking> cusBookingInfo = new ArrayList<>();

    public BookingQueries() {
        try{
            this.connection = getConnection();
            this.statement = connection.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
    public void customerBookingInfo(String id){
        try {
            String selQueries = " select b.bookingID,  CONCAT(firstName, ' ', lastName) AS Name,  p.packageName, b.date bookingDate, p.price, " +
                    "f.departureDate, f.returnDate, f.departureCity, f.arrivalCity, f.flightName, h.hotelName, h.numOfNights," +
                    " h.typeOfRoom, c.carType from booking b join booking_has_package bhp On bhp.booking_bookingID = b.bookingID " +
                    "join package p On bhp.package_packageId = p.packageId Join flight f On p.flight_flightID = f.flightID " +
                    "join hotel h on p.hotel_hotelID = h.hotelID join car c on p.car_carID =  c.carID " +
                    "join user u  on b.user_SSN = u.SSN where SSN = " + "\'" + id + "\'" ;
            //resultSet = statement.executeQuery(selQueries);
            resultSet = connection.createStatement().executeQuery(selQueries);
            while (resultSet.next()) {
                Booking booking = new Booking("",  "", "", "", "", "",
                        "", "", "", "", "", "", "", "");
                booking.setBookingID(resultSet.getString("bookingId"));
                booking.setName(resultSet.getString("Name"));
                booking.setPackageName(resultSet.getString("packageName"));
                booking.setBookingDate(resultSet.getString("bookingDate"));
                booking.setPrice(resultSet.getString("price"));
                booking.setDepartureDate(resultSet.getString("departureDate"));
                booking.setReturnDate(resultSet.getString("returnDate"));
                booking.setDepartureCity(resultSet.getString("departureCity"));
                booking.setArrivalCity(resultSet.getString("arrivalCity"));
                booking.setFlightName(resultSet.getString("flightName"));
                booking.setHotelName(resultSet.getString("hotelName"));
                booking.setNumOfNights(resultSet.getString("numOfNights"));
                booking.setTypeOfRoom(resultSet.getString("typeOfRoom"));
                booking.setCarType(resultSet.getString("carType"));

                cusBookingInfo.add(booking);
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

    public ArrayList<Booking> getCusBookingInfo() {
        return cusBookingInfo;
    }

    public void setCusBookingInfo(ArrayList<Booking> cusBookingInfo) {
        this.cusBookingInfo = cusBookingInfo;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }
}
