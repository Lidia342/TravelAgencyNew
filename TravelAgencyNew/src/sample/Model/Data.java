package sample.Model;

//import Database.DatabaseConnection;

public class Data {

    private static Data myData = new Data();
    private User user;
    private Package currentPackage;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Data(){}

    public static Data getInstance(){

        return myData;
    }

    public void setCurrentPackage(Package currentPackage){

        this.currentPackage=currentPackage;
    }

    public Package getCurrentPackage() {
        return currentPackage;
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
