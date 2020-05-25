package sample.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import sample.Model.AdminPackageTable;
import sample.Model.CustomerPackageTable;
import sample.Model.Data;
import sample.Model.Package;

import java.sql.*;
import java.util.ArrayList;

public class PackageQueries extends DatabaseConnection {

    private ObservableList<Object> oList = FXCollections.observableArrayList();

    private Connection connection;

    private ArrayList<CustomerPackageTable> list = new ArrayList<>();

    public ArrayList<CustomerPackageTable> getList() {
        return list;
    }

    public void setList(ArrayList<CustomerPackageTable> list) {
        this.list = list;
    }


    Data myData = Data.getInstance();

    public PackageQueries() {
        try{
            this.connection = getConnection();
            Statement statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
<<<<<<< HEAD
    public void getPackageInfo(String name) throws SQLException {
        try {
            String selectQuery = "select p.packageName, f.departureDate, f.returnDate, f.departureCity, f.arrivalCity," +
                " f.flightName, h.hotelName, h.hotelID, h.typeOfRoom, c.carType," +
                " p.price, p.flight_flightID, p.car_carID, p.hotel_hotelID, h.numOfNights, c.carNumOfDays from package p Join flight f On p.flight_flightID = f.flightID join hotel h " +
                "on p.hotel_hotelID = h.hotelID join car c on p.car_carID =  c.carID where packageName =  " + "\'" + name + "\'";


            ResultSet resultSet = connection.createStatement().executeQuery(selectQuery);

            while (resultSet.next()){
                CustomerPackageTable pt = new CustomerPackageTable("packageName", "departureDate","returnDate",
                        "departureCity", "arrivalCity", "flightName", "hotelName",
                        "numOfNights", "typeOfRoom", "carType", "price");
                Package currentPackage = new Package(resultSet.getString("packageName")
                        , resultSet.getString("flight_flightID"), resultSet.getString("car_carID"), resultSet.getString("hotel_hotelID"),
                        resultSet.getString("hotelName"), resultSet.getString("carType"),
                        Integer.parseInt(resultSet.getString("numOfNights")), resultSet.getString("departureCity"),
                        resultSet.getString("arrivalCity"), Integer.parseInt(resultSet.getString("carNumOfDays")), Double.parseDouble(resultSet.getString("price"))
                , resultSet.getString("departureDate"), resultSet.getString("returnDate"));

                myData.setCurrentPackage(currentPackage);

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

                //System.out.println(resultSet.getString("packageName"));




        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateDate(String departureDate, String returnDate, String flightID){
=======

   public void viewPackage(String name){

       try {
           String selectQuery = "select p.packageName, f.departureDate, f.returnDate, f.departureCity, f.arrivalCity," +
                   " f.flightName, h.hotelName, h.numOfNights, h.typeOfRoom, c.carType," +
                   " p.price from package p Join flight f On p.flight_flightID = f.flightID join hotel h " +
                   "on p.hotel_hotelID = h.hotelID join car c on p.car_carID =  c.carID where packageName =  " + "\'" + name + "\'";


           ResultSet resultSet = connection.createStatement().executeQuery(selectQuery);

           while (resultSet.next()){
               CustomerPackageTable customerPackageTable = new CustomerPackageTable("","","",
                       "","","","","","","","");
               customerPackageTable.setPackageName(resultSet.getString("packageName"));
               customerPackageTable.setDepartureDate(resultSet.getString("departureDate"));
               customerPackageTable.setReturnDate(resultSet.getString("returnDate"));
               customerPackageTable.setDepartureCity(resultSet.getString("departureCity"));
               customerPackageTable.setArrivalCity(resultSet.getString("arrivalCity"));
               customerPackageTable.setFlightName(resultSet.getString("flightName"));
               customerPackageTable.setHotelName(resultSet.getString("hotelName"));
               customerPackageTable.setNights(resultSet.getString("numOfNights"));
               customerPackageTable.setRoomType(resultSet.getString("typeOfRoom"));
               customerPackageTable.setCarType(resultSet.getString("carType"));
               customerPackageTable.setPrice(resultSet.getString("price"));
               list.add(customerPackageTable);

               setList(list);

           }
       } catch (SQLException ex) {
           ex.printStackTrace();
       }

   }
       public void updateDate(String departureDate, String returnDate, String flightID){
>>>>>>> 4a3e242822fa2deed6eeb7b47b67d54d34431f9a
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

}
