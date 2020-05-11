package sample.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.packageTable;

import java.sql.*;

public class PackageQueries extends DatabaseConnection {

    private ObservableList<Object> obList = FXCollections.observableArrayList();

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

        String selectQuery = "select p.packageName, f.flightDepartureDate, f.departing_city, f.arriving_city," +
                " f.flightName, h.hotelName, h.numOfNights, h.typeOfRoom, c.carType," +
                " p.price from package p Join flight f On p.flight_flightID = f.flightID join hotel h " +
                "on p.hotel_hotelID = h.hotelID join car c on p.car_carID =  c.carID where packageName =  " + "\'" + name + "\'";


        ResultSet resultSet = connection.createStatement().executeQuery(selectQuery);

        while (resultSet.next()){
            packageTable pt = new packageTable("packageName", "flightDepartureDate",
                    "departing_city", "arriving_city", "flightName", "hotelName",
                    "numOfNights", "typeOfRoom", "carType", "price");
            pt.setPackageName(resultSet.getString("packageName"));
            pt.setFlightDepartureDate(resultSet.getString("flightDepartureDate"));
            pt.setDeparting_city(resultSet.getString("departing_city"));
            pt.setArriving_city(resultSet.getString("arriving_city"));
            pt.setFlightName(resultSet.getString("flightName"));
            pt.setHotelName(resultSet.getString("hotelName"));
            pt.setNumOfNights(resultSet.getString("numOfNights"));
            pt.setTypeOfRoom(resultSet.getString("typeOfRoom"));
            pt.setCarType(resultSet.getString("carType"));
            pt.setPrice(resultSet.getString("price"));

            obList.add(pt);
            setObList(obList);


        }
    }

    public ObservableList<Object> getObList() {
        return obList;
    }


    public void setObList(ObservableList<Object> obList) {
        this.obList = obList;
    }
}
