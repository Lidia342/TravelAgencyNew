package sample.Model;

//import Database.DatabaseConnection;

import java.sql.Connection;

public class Data {

    private static Data myData;
    private Connection connection;
    //private DatabaseConnection databaseConnection;
    private int userID;
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Data(){}

    public static Data getInstance(){

        if (myData==null){

            Data myData = new Data();
        }
        return myData;
    }

    public Connection getConnection(){
        return connection;
    }

    public void setConnection(){
        this.connection=connection;
    }


    /*
    public void setDatabaseConnection(DatabaseConnection databaseConnection) {
        this.dBconnection = dBconnection;
    }

    public DatabaseConnection getDatabaseConnection() {
        return databaseConnection;
    }

    public void setdBconnection(DBconnection dBconnection) {
        this.dBconnection = dBconnection;
    }

     */
}
