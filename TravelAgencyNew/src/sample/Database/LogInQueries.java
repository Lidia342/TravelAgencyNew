package sample.Database;

import java.sql.*;

public class LogInQueries extends DatabaseConnection{

    private Connection connection;
    private ResultSet resultSet;
    public LogInQueries() {
        try {
            this.connection = getConnection();
            Statement statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean validate(String emailId, String password){

        String select = "SELECT * FROM user WHERE email = ? and password = ? and SSN ='199205134562' ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);  {
                preparedStatement.setString(1, emailId);
                preparedStatement.setString(2, password);

                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()){
                    return true;
                }


            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());        }
        return false;
    }
    public boolean customerLogin(String emailId, String password){

        String select = "SELECT * FROM user WHERE email = ? and password = ? and SSN !='199205134562' ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);  {
                preparedStatement.setString(1, emailId);
                preparedStatement.setString(2, password);

                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()){
                    return true;
                }

            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());        }
        return false;
    }
}
