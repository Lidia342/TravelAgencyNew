package sample.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.BookingTable;

import java.sql.*;

public class BookingQueries extends DatabaseConnection {

    private ObservableList<Object> obList = FXCollections.observableArrayList();

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public BookingQueries() {
        try{
            this.connection = getConnection();
            this.statement = connection.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertBookedPackageIntoBookingTable(String date, String user_SSN){

       // String BookingQuery = "INSERT INTO booking (date, user_SSN, package_packageId) " +
         //       "VALUES (?, ?, ?)";
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

    public void viewBooked(){
        try{
            String selectQuery = "select b.bookingID, u.SSN, u.firstName, u.lastName, p.packageName, b.date, p.price from booking b " +
                    "join booking_has_package bhp On bhp.booking_bookingID = b.bookingID join package p " +
                    "On bhp.package_packageId = p.packageId join user u  on b.user_SSN = u.SSN;";

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



    /*public int getPackageId(String name){
        int packageId = 0;
        String query = "select packageId" +
                "from package where packageName = " + "\'" + name + "\'";
        try{
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                packageId = resultSet.getInt("id");
            } return packageId;
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println( e.getMessage());
            return 0;
        }
    }*/

    public ObservableList<Object> getObList() {
        return obList;
    }

    public void setObList(ObservableList<Object> obList) {
        this.obList = obList;
    }
}
