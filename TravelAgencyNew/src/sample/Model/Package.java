package sample.Model;

public class Package {

    public String flightID;
    public String carID;
    public String hotelID;
    public int hotelNights;
    public String departingCity;
    public String arrivingCity;
    public int carRentalDays;
    public String packageName;
    public double price;
    public String departure_time;
    public String return_time;
    public String hotelName;
    public String carModel;

    public Package(String packageName, String flightID, String carID, String hotelID, String hotelName, String carModel,
                   int hotelNights, String departingCity, String arrivingCity, int carRentalDays,
                   double price, String departure_time, String return_time) {
        this.packageName=packageName;
        this.flightID = flightID;
        this.hotelName = hotelName;
        this.carID = carID;
        this.hotelID = hotelID;
        this.hotelNights = hotelNights;
        this.departingCity = departingCity;
        this.arrivingCity = arrivingCity;
        this.carRentalDays = carRentalDays;
        this.price = price;
        this.departure_time = departure_time;
        this.return_time = return_time;
        this.carModel = carModel;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public String getReturn_time() {
        return return_time;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public void setHotelID(String hotelID) {
        this.hotelID = hotelID;
    }

    public void setHotelNights(int hotelNights) {
        this.hotelNights = hotelNights;
    }

    public void setDepartingCity(String departingCity) {
        this.departingCity = departingCity;
    }

    public void setArrivingCity(String arrivingCity) {
        this.arrivingCity = arrivingCity;
    }

    public void setCarRentalDays(int carRentalDays) {
        this.carRentalDays = carRentalDays;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getFlightID() {
        return flightID;
    }

    public String getCarID() {
        return carID;
    }

    public String getHotelID() {
        return hotelID;
    }

    public int getHotelNights() {
        return hotelNights;
    }

    public String getDepartingCity() {
        return departingCity;
    }

    public String getArrivingCity() {
        return arrivingCity;
    }

    public int getCarRentalDays() {
        return carRentalDays;
    }

    public String getPackageName() {
        return packageName;
    }

    public double getPrice() {
        return price;
    }
}
