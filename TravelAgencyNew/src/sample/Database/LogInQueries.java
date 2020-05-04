package sample.Database;

import java.sql.*;

public class LogInQueries extends DatabaseConnection{

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    public LogInQueries() {
        try {

            this.connection = getConnection();
            this.statement = connection.createStatement();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean validate(String emailId, String password){

        String select = "SELECT * FROM person WHERE email = ? and password = ? and SSN ='199205134561' ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);  {
                preparedStatement.setString(1, emailId);
                preparedStatement.setString(2, password);

                ResultSet rs = preparedStatement.executeQuery();

                if (rs.next()){
                    return true;
                }


            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());        }
        return false;
    }
    public boolean customerLogin(String emailId, String password){

        String select = "SELECT * FROM person WHERE email = ? and password = ? and SSN !='199205134561' ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);  {
                preparedStatement.setString(1, emailId);
                preparedStatement.setString(2, password);

                ResultSet rs = preparedStatement.executeQuery();

                if (rs.next()){
                    return true;
                }


            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());        }
        return false;
    }
}
