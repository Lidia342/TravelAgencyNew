package Database;

import java.sql.*;

public class DatabaseConnection {

    Connection connection = null;

    public Connection connect(){

        String url = "jdbc:mysql://den1.mysql5.gear.host:3306/travelagency1";
        String username = "travelagency1";
        String password = "Cw0Mr?!4KN2V";

        System.out.println("loading...");

        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected!");
            return connection;
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }
    public void view(){  //testing the database privilege
        try {
            String url = "jdbc:mysql://den1.mysql5.gear.host:3306/travelagency1";
            String username = "travelagency1";
            String password = "Cw0Mr?!4KN2V";

            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT  firstName,lastName, email FROM travelagency1.person;");
            while ( rs.next() ) {
                System.out.println("employees name :  " + rs.getString(1) +

                        "     "+ rs.getString(2) +"     "+
                        " email : " +
                        rs.getString(3));
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }


    }
}

