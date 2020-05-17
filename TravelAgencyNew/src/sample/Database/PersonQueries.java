package sample.Database;

import sample.Model.User;

import java.sql.*;
import java.util.ArrayList;

public class PersonQueries extends DatabaseConnection {
    private Connection connection;
    private Statement statement;
    private ArrayList<User> personInfo;
    private ResultSet rst;


    public PersonQueries() {
        try {

            this.connection = getConnection();
            this.statement = connection.createStatement();
            personInfo = new ArrayList<>();


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
            // preparedStatement.setInt(8, customerId);
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
            // preparedStatement.setInt(8, customerId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void personTableSelect(){


        String selectQuery = "SELECT * FROM user WHERE SSN != 199205134561";

        try{
            rst = statement.executeQuery(selectQuery);

            ArrayList<User> personData = new ArrayList<>();

            while(rst.next()){
                personData.add( new User(rst.getString("SSN"),
                rst.getString("firstName"),
                rst.getString("lastName"),
                rst.getString("phoneNumber"),
                rst.getString("email"),
                rst.getString("password"),
                rst.getString("address"),
                rst.getString("type")));

            }

        } catch (SQLException e) {
            e.printStackTrace();
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

        String editQuery= "UPDATE Users SET password=? WHERE id=?";
        PreparedStatement preparedStmt = connection.prepareStatement(editQuery);
        preparedStmt.setString (1, password);
        preparedStmt.setString (2, userID);

        if(preparedStmt.execute()){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean editFirstName(String newName, String userID) throws SQLException {

        String editQuery= "UPDATE Users SET first_name=? WHERE id=?";
        PreparedStatement preparedStmt = connection.prepareStatement(editQuery);
        preparedStmt.setString (1, newName);
        preparedStmt.setString (2, userID);

        if(preparedStmt.execute()){
            return true;
        }
        else {
            return false;
        }
    }

    public ArrayList<User> getPersonInfo() {
        return personInfo;
    }

    public boolean editLastName(String newName, String userID) throws SQLException {
        String editQuery= "UPDATE Users SET last_name=? WHERE id=?";
        PreparedStatement preparedStmt = connection.prepareStatement(editQuery);
        preparedStmt.setString (1, newName);
        preparedStmt.setString (2, userID);

        if(preparedStmt.execute()){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean editEmail(String newEmail, String userID) throws SQLException {

        if(!emailExists(newEmail)){
            String editQuery= "UPDATE Users SET email=? WHERE id=?";
            PreparedStatement preparedStmt = connection.prepareStatement(editQuery);
            preparedStmt.setString (1, newEmail);
            preparedStmt.setString (2, userID);

            if(!preparedStmt.execute()){
                return false;
            }

            return true;
        }
        else return false;
    }
}
