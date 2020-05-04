package sample.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DatabaseConnection {
    private Connection connection;

    public  DatabaseConnection(){


        String url = "jdbc:mysql://den1.mysql5.gear.host:3306/travelagency1";
        String userName = "travelagency1";
        String password = "Cw0Mr?!4KN2V";

        System.out.println("loading...");

        try{ connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Database connected!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}

