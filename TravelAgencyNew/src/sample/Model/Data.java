package sample.Model;
public class Data {

    private static Data myData = new Data();
    private User user;
    private Package currentPackage;
    private Booking currentBooking;

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

    public void setCurrentBooking(Booking currentBooking) {
        this.currentBooking=currentBooking;
    }

    public Booking getCurrentBooking() {
        return currentBooking;
    }

}
