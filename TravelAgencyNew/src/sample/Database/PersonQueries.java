package sample.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.UserTable;

import java.sql.*;

public class PersonQueries extends DatabaseConnection {
    private Connection connection;
    private Statement statement;
    private ResultSet rst;

    private ObservableList<Object> obsList = FXCollections.observableArrayList();
    private ObservableList<Object> list = FXCollections.observableArrayList();
    private ObservableList<Object> searchList = FXCollections.observableArrayList();

    public PersonQueries() {
        try {
            this.connection = getConnection();
            this.statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPersonInformation(String SSN, String firstName, String lastName,
                                        String phoneNumber, String email, String password,
                                        String address, String type) {

        String CustomerQuery = "INSERT INTO user(SSN, firstName, lastName, phoneNumber, email, " +
                "password, address, type) Values (?,?,?,?,?,?,?,?)";
        personTablePreparedStatement (SSN, firstName, lastName, phoneNumber, email, password, address,type,CustomerQuery);

    }
    private void personTablePreparedStatement(String SSN, String firstName, String lastName,
                                                String phoneNumber, String email, String password, String address, String type,
                                                String query){

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, SSN);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setString(4, phoneNumber);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, password);
            preparedStatement.setString(7, address);
            preparedStatement.setString(8,type);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void updatePersonTable(String phoneNumber, String password, String address,
                                  String email, String SSN){
        String updateQuery = "UPDATE user SET phoneNumber = ?, email = ?, password = ?, address = ?  WHERE SSN = ?";


        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, phoneNumber);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, SSN);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void personTableSelect(String name) {

       // String selectQuery = "SELECT * FROM user WHERE SSN != 199205134562 and firstName =  " + "\' " + name +  "\' ";
        String selectQuery = "SELECT * FROM user WHERE SSN != 199205134562 AND firstName = " + "\'" + name + "\'";

        try{
            ResultSet resultSet = connection.createStatement().executeQuery(selectQuery);
          //  ps.setString(1,name);

            while (resultSet.next()) {
                UserTable ut = new UserTable("SSN", "firstName", "lastName",
                        "phoneNumber", "email", "password", "address", "type");


                ut.setSSN(resultSet.getString("SSN"));
                ut.setFirstName(resultSet.getString("firstName"));
                ut.setLastName(resultSet.getString("lastName"));
                ut.setPhoneNumber(resultSet.getString("phoneNumber"));
                ut.setEmail(resultSet.getString("email"));
                ut.setPassword(resultSet.getString("password"));
                ut.setAddress(resultSet.getString("address"));
                ut.setType(resultSet.getString("type"));
                searchList.add(ut);

            }
            setSearchList(searchList);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void viewCustomer() {
        String selQuery = "SELECT * FROM user WHERE SSN != 199205134562";

        try{
            ResultSet resultSet = connection.createStatement().executeQuery(selQuery);
            while (resultSet.next()) {
                UserTable ut = new UserTable("SSN", "firstName", "lastName",
                        "phoneNumber", "email", "password", "address", "type");


                ut.setSSN(resultSet.getString("SSN"));
                ut.setFirstName(resultSet.getString("firstName"));
                ut.setLastName(resultSet.getString("lastName"));
                ut.setPhoneNumber(resultSet.getString("phoneNumber"));
                ut.setEmail(resultSet.getString("email"));
                ut.setPassword(resultSet.getString("password"));
                ut.setAddress(resultSet.getString("address"));
                ut.setType(resultSet.getString("type"));
                obsList.add(ut);

            }
            setObsList(obsList);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void removeCustomer(String ssn){
        String removeQuery = "Delete From user Where SSN =?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(removeQuery);

            preparedStatement.setString(1, ssn);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }


    private boolean existence(String attribute, String query) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, attribute);


            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return true;
        }
    }
    public boolean emailExists(String email) {
        String emailQuery = "SELECT * FROM user WHERE email = ?";

        if (existence(email, emailQuery)) {
            return true;
        } else {
            return false;
        }

    }

    public boolean userNotExists(String SSN) {
        String SSNQuery = "SELECT * FROM user WHERE SSN = ?";

        if (existence(SSN, SSNQuery)) {
            return false;
        } else {
            return true;
        }

    }

    public void sendPassword(String password, String ssn){

        try {
          String update ="Update user set password = ? where SSN = ? ";

            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2,ssn);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public ObservableList<Object> getObsList() {
        return obsList;
    }

    public void setObsList(ObservableList<Object> obsList) {
        this.obsList = obsList;
    }

    public ObservableList<Object> getList() {
        return list;
    }

    public void setList(ObservableList<Object> list) {
        this.list = list;
    }

    public ObservableList<Object> getSearchList() {
        return searchList;
    }

    public void setSearchList(ObservableList<Object> searchList) {
        this.searchList = searchList;
    }
}
