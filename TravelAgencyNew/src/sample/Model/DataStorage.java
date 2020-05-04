package sample.Model;

import java.util.ArrayList;

public class DataStorage {

    private static DataStorage Instance = new DataStorage();

    private ArrayList<User> personData;

    private DataStorage(){

    }
    public static DataStorage getInstance(){
        return Instance;
    }

    public ArrayList<User> getPersonData() {
        return personData;
    }

    public void setPersonData(ArrayList<User> personData) {
        this.personData = personData;
    }

}
