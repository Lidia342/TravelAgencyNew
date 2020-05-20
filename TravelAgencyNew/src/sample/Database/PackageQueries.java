package sample.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import sample.Model.AdminPackageTable;
import sample.Model.CustomerPackageTable;

import java.sql.*;

public class PackageQueries extends DatabaseConnection {

    private ObservableList<Object> obList = FXCollections.observableArrayList();
    private ObservableList<Object> oList = FXCollections.observableArrayList();

    private Connection connection;
    private Statement statement;

    public PackageQueries() {
        try{
            this.connection = getConnection();
            this.statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void getPackageInfo(String name) throws SQLException {
        try {
            String selectQuery = "select p.packageName, f.departureDate, f.returnDate, f.departureCity, f.arrivalCity," +
                " f.flightName, h.hotelName, h.numOfNights, h.typeOfRoom, c.carType," +
                " p.price from package p Join flight f On p.flight_flightID = f.flightID join hotel h " +
                "on p.hotel_hotelID = h.hotelID join car c on p.car_carID =  c.carID where packageName =  " + "\'" + name + "\'";


            ResultSet resultSet = connection.createStatement().executeQuery(selectQuery);

            while (resultSet.next()){
                CustomerPackageTable pt = new CustomerPackageTable("packageName", "departureDate","returnDate",
                        "departureCity", "arrivalCity", "flightName", "hotelName",
                        "numOfNights", "typeOfRoom", "carType", "price");
                pt.setPackageName(resultSet.getString("packageName"));
                pt.setDepartureDate(resultSet.getString("departureDate"));
                pt.setReturnDate(resultSet.getString("returnDate"));
                pt.setDepartureCity(resultSet.getString("departureCity"));
                pt.setArrivalCity(resultSet.getString("arrivalCity"));
                pt.setFlightName(resultSet.getString("flightName"));
                pt.setHotelName(resultSet.getString("hotelName"));
                pt.setNumOfNights(resultSet.getString("numOfNights"));
                pt.setTypeOfRoom(resultSet.getString("typeOfRoom"));
                pt.setCarType(resultSet.getString("carType"));
                pt.setPrice(resultSet.getString("price"));

                obList.add(pt);
                setObList(obList);}



        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void updateDate(String departureDate, String returnDate, String flightID){
        String updateQuery = "UPDATE flight SET departureDate = ?, returnDate = ?  WHERE flightId = ?";


        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, departureDate);
            preparedStatement.setString(2, returnDate);
            preparedStatement.setString(3, flightID);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Alert er = new Alert(Alert.AlertType.ERROR);
            er.setTitle("Invalid Input!");
            er.setHeaderText("Write Date and time in correct format ");
            er.show();
        }
    }

    public void adminPackage(){
        try {
            String selectQuery = "select f.flightID, p.packageName, f.departureDate, f.returnDate, f.departureCity, f.arrivalCity, " +
                    "f.flightName, h.hotelName, h.numOfNights, h.typeOfRoom, c.carType, p.price from package p Join flight f " +
                    "On p.flight_flightID = f.flightID join hotel h on p.hotel_hotelID = h.hotelID join car c on p.car_carID =  c.carID " +
                    "order by f.departureDate asc";


            ResultSet resultSet = connection.createStatement().executeQuery(selectQuery);

            while (resultSet.next()){
                AdminPackageTable adminPackageTable = new AdminPackageTable("flightID","packageName", "departureDate","returnDate",
                        "departureCity", "arrivalCity", "flightName", "hotelName",
                        "numOfNights", "typeOfRoom", "carType", "price");
                adminPackageTable.setFlightID(resultSet.getString("flightID"));
                adminPackageTable.setPackageName(resultSet.getString("packageName"));
                adminPackageTable.setDepartureDate(resultSet.getString("departureDate"));
                adminPackageTable.setReturnDate(resultSet.getString("returnDate"));
                adminPackageTable.setDepartureCity(resultSet.getString("departureCity"));
                adminPackageTable.setArrivalCity(resultSet.getString("arrivalCity"));
                adminPackageTable.setFlightName(resultSet.getString("flightName"));
                adminPackageTable.setHotelName(resultSet.getString("hotelName"));
                adminPackageTable.setNumOfNights(resultSet.getString("numOfNights"));
                adminPackageTable.setTypeOfRoom(resultSet.getString("typeOfRoom"));
                adminPackageTable.setCarType(resultSet.getString("carType"));
                adminPackageTable.setPrice(resultSet.getString("price"));

                oList.add(adminPackageTable);
                setOList(oList);}



        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ObservableList<Object> getOList() {
        return oList;
    }

    public void setOList(ObservableList<Object> oList) {
        this.oList = oList;
    }

    public ObservableList<Object> getObList() {
        return obList;
    }


    public void setObList(ObservableList<Object> obList) {
        this.obList = obList;
    }
}
