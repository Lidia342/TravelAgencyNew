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

    public void insertBookedPackageIntoBookingTable(String date, String user_SSN, int package_packageId){

        String BookingQuery = "INSERT INTO booking (date, user_SSN, package_packageId) " +
                "VALUES (?, ?, ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(BookingQuery)){
            preparedStatement.setString(1, date);
            preparedStatement.setString(2, user_SSN);
            preparedStatement.setInt(3,package_packageId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());        }
    }

    public void viewBooked(){
        try{
            String selectQuery = "select u.SSN, u.firstName, u.lastName, p.packageName, b.date, p.price from booking " +
                    "b join package p On b.package_packageId = p.packageId join user u  on b.user_SSN = u.SSN;";

            ResultSet resultSet = connection.createStatement().executeQuery(selectQuery);
            while (resultSet.next()) {
                BookingTable bookingTable = new BookingTable("SSN","firstName","lastName",
                        "packageName","date","price");
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
        String removeQuery = "Delete From booking Where user_SSN =?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(removeQuery);

            preparedStatement.setString(1, ssn);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
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
