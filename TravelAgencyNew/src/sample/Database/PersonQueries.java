package sample.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.PersonTable;

import java.sql.*;

public class PersonQueries extends DatabaseConnection {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;


    public PersonQueries() {
        try {

            this.connection = getConnection();
            this.statement = connection.createStatement();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPersonInformation(String SSN, String firstName, String lastName,
                                        String phoneNumber, String password, String address,
                                        String email) {

        String CustomerQuery = "INSERT INTO person(SSN, firstName, lastName, phoneNumber, email, " +
                "password, address) Values (?,?,?,?,?,?,?)";
        personTablePreparedStatement (SSN, firstName, lastName, phoneNumber, email, password, address,CustomerQuery);

    }
    private void personTablePreparedStatement(String SSN, String firstName, String lastName,
                                                String phoneNumber, String email, String password, String address,
                                                String query){

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, SSN);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setString(4, phoneNumber);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, password);
            preparedStatement.setString(7, address);
            // preparedStatement.setInt(8, customerId);
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
        String emailQuery = "SELECT * FROM person WHERE email = ?";

        if (existence(email, emailQuery)) {
            return true;
        } else {
            return false;
        }

    }

    public void selectPersonInfo() throws SQLException {

        ObservableList<PersonTable> obsList = FXCollections.observableArrayList();

        String selectQuery = "SELECT * FROM person WHERE SSN != 199205134561";

        resultSet = connection.createStatement().executeQuery(selectQuery);
        while (resultSet.next()){
            PersonTable ut = new PersonTable("SSN", "firstName", "lastName",
                    "phoneNumber","email", "password", "address");


            ut.setSSN(resultSet.getString("SSN"));
            ut.setFirstName(resultSet.getString("firstName"));
            ut.setLastName(resultSet.getString("lastName"));
            ut.setPhoneNumber(resultSet.getString("phoneNumber"));
            ut.setEmail(resultSet.getString("email"));
            ut.setPassword(resultSet.getString("password"));
            ut.setAddress(resultSet.getString("address"));
            obsList.add(ut);

    }

    }

    public void viewPersonInformation(String SSN, String phoneNumber, String password, String address,
                                     String email) {

        String updateQuery = "UPDATE person SET phoneNumber = ?, email = ?, password = ?, address = ?  WHERE SSN = ?";
        personTablePreparedStatement1 (phoneNumber, email, password, address, SSN,updateQuery);

    }
    private void personTablePreparedStatement1(String SSN,
                                              String phoneNumber, String email, String password, String address,
                                              String query){

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, phoneNumber);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, SSN);
            // preparedStatement.setInt(8, customerId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }








    /*public int getLastCustomerId(){

        try {
            int lastCustomerID = -1;
            resultSet = statement.executeQuery("select MAX(id) FROM customers");

            while (resultSet.next()) {
                lastCustomerID = resultSet.getInt("id");
            }
            return lastCustomerID;
        } catch (SQLException m) {
            System.out.println(m.getMessage());
            return 0;
        }
    }*/
}
