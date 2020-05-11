package sample.Model;

public class Package {

    public int packageID;
    public Flight flight;
    public Car car;
    public Hotel hotel;
    public int hotelNights;
    public String departingCity;
    public String arrivingCity;
    public String name;

    public Package(int packageID, Flight flight, Car car, Hotel hotel, int hotelNights, String departingCity, String arrivingCity) {
        this.packageID = packageID;
        this.flight = flight;
        this.car = car;
        this.hotel = hotel;
        this.hotelNights = hotelNights;
        this.departingCity = departingCity;
        this.arrivingCity = arrivingCity;
    }

    public void setPackageID(int packageID) {
        this.packageID = packageID;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
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

    public int getPackageID() {
        return packageID;
    }

    public Flight getFlight() {
        return flight;
    }

    public Car getCar() {
        return car;
    }

    public Hotel getHotel() {
        return hotel;
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

    public String getName() {
        return name;
    }
}
