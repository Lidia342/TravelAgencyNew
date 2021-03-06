package sample.Database;

import sample.Model.User;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.*;
import java.util.ArrayList;

public class LogInQueries extends DatabaseConnection {

    private Connection connection;
    private ResultSet resultSet;
    PersonQueries pQ = new PersonQueries();

    public LogInQueries() {
        try {
            this.connection = getConnection();
            Statement statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getPasswordEnc(String emailId) throws SQLException {

        String select = "SELECT password FROM user WHERE email = ?";
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.setString(1, emailId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.first();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet.getString(1);
    }

    public boolean validate(String emailId, String password) throws GeneralSecurityException, IOException {

        String select = "SELECT * FROM user WHERE email = ? and password = ? and SSN ='199205134562' ";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(select);
                {
                    preparedStatement.setString(1, emailId);
                    preparedStatement.setString(2, password);

                    resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        return true;
                    }

                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return false;
    }

    public boolean customerLogin(String emailId, String password) throws GeneralSecurityException, IOException {

        String select = "SELECT * FROM user WHERE email = ? and password = ? and SSN !='199205134562' ";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(select);
                {
                    preparedStatement.setString(1, emailId);
                    preparedStatement.setString(2, password);
                    resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {

                        return true;
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return false;
    }

    public User establishCurrentCustomer(String email, String password) {

        ArrayList<String> paramters= new ArrayList<>();
        String select = "SELECT * FROM user WHERE email = ? and password = ?";
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.first();

            for(int x=1; x<9;x++) {
                //System.out.println(resultSet.getString(x));
                paramters.add(resultSet.getString(x));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new User(paramters.get(0),paramters.get(1),paramters.get(2),
                paramters.get(3),paramters.get(4),paramters.get(5),paramters.get(6), paramters.get(7));

            }
        }

