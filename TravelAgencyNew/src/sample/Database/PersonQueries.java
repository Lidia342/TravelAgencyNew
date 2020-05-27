package sample.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.Data;
import sample.Model.UserTable;

import java.sql.*;

public class PersonQueries extends DatabaseConnection {
    private Connection connection;
    private Statement statement;
    private ResultSet rst;
    Data myData = Data.getInstance();

    private ObservableList<Object> obsList = FXCollections.observableArrayList();

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
        personTablePreparedStatement(SSN, firstName, lastName, phoneNumber, email, password, address, type, CustomerQuery);

    }

    private void personTablePreparedStatement(String SSN, String firstName, String lastName,
                                              String phoneNumber, String email, String password, String address, String type,
                                              String query) {

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, SSN);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setString(4, phoneNumber);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, password);
            preparedStatement.setString(7, address);
            preparedStatement.setString(8, type);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updatePersonTable(String phoneNumber, String password, String address,
                                  String email, String SSN) {
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

    public void personTableSelect() {
        String selectQuery = "SELECT * FROM user WHERE SSN != 199205134561";

        try {
            ResultSet resultSet = connection.createStatement().executeQuery(selectQuery);
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

    public void removeCustomer(String ssn) {
        String removeQuery = "Delete From user Where SSN =?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(removeQuery);

            preparedStatement.setString(1, ssn);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
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

    public boolean editPassword(String password, String userID) throws SQLException {

        String editQuery = "UPDATE user SET password=? WHERE SSN=?";
        PreparedStatement preparedStmt = connection.prepareStatement(editQuery);
        preparedStmt.setString(1, password);
        preparedStmt.setString(2, userID);

        if (preparedStmt.execute()) {
            myData.getUser().setPassword(password);
            return true;
        } else {
            return false;
        }
    }

    public void editFirstName(String newName, String userID) throws SQLException {

        try {


            String editQuery = "UPDATE user SET firstName=? WHERE SSN=?";
            PreparedStatement preparedStmt = connection.prepareStatement(editQuery);
            preparedStmt.setString(1, newName);
            preparedStmt.setString(2, userID);
            preparedStmt.executeUpdate();
            myData.getUser().setFirstName(newName);
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }


    public void editLastName(String newName, String userID) throws SQLException {

        try {
            String editQuery = "UPDATE user SET lastName=? WHERE SSN=?";
            PreparedStatement preparedStmt = connection.prepareStatement(editQuery);
            preparedStmt.setString(1, newName);
            preparedStmt.setString(2, userID);
            preparedStmt.executeUpdate();
            myData.getUser().setLastName(newName);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void editEmail(String newEmail, String userID) throws SQLException {

        if (!emailExists(newEmail)) {
            String editQuery = "UPDATE user SET email=? WHERE SSN=?";
            PreparedStatement preparedStmt = connection.prepareStatement(editQuery);
            preparedStmt.setString(1, newEmail);
            preparedStmt.setString(2, userID);
            preparedStmt.executeUpdate();
            myData.getUser().setEmail(newEmail);
        }
    }


    public void sendPassword(String password, String ssn) {

        try {
            String update = "Update user set password = ? where SSN = ? ";

            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, ssn);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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

    public ObservableList<Object> getObsList() {
        return obsList;
    }

    public void setObsList(ObservableList<Object> obsList) {
        this.obsList = obsList;
    }

    public boolean editAddress(String address, String ssn) throws SQLException {
        String editQuery = "UPDATE user SET address=? WHERE SSN=?";
        PreparedStatement preparedStmt = connection.prepareStatement(editQuery);
        preparedStmt.setString(1, address);
        preparedStmt.setString(2, ssn);

        if (preparedStmt.execute()) {
            myData.getUser().setAddress(address);
            return true;
        } else {
            return false;
        }
    }
}