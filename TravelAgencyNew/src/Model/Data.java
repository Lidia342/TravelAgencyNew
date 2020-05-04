package Model;

import Database.DatabaseConnection;

import java.net.DatagramSocket;

public class Data {

    private static Data myData;
    private DBconnection dBconnection;
    private DatabaseConnection databaseConnection;
    private int userID;

    public void setUserID(int customerID) {
        this.userID = customerID;
    }

    public int getUserID() {
        return userID;
    }

    private Data(){}

    public static Data getInstance(){

        if (myData==null){

            Data myData = new Data();
        }
        return myData;
    }

    public DBconnection getDBConnection(){
        return dBconnection;
    }

    public void setDatabaseConnection(DatabaseConnection databaseConnection) {
        this.dBconnection = dBconnection;
    }

    public DatabaseConnection getDatabaseConnection() {
        return databaseConnection;
    }

    public void setdBconnection(DBconnection dBconnection) {
        this.dBconnection = dBconnection;
    }
}
