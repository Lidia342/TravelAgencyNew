package sample.Model;

import java.util.Date;

public class Hotel {

    public String hotelName;
    private int hotelID;
    public int stars;
    public String hotelAddress;
    public double dailyPrice;
    public String city;
    public String county;
    public int availableRooms;

    public Hotel(String hotelName, int hotelID, int stars, String hotelAddress, double dailyPrice, String city, String county, Date checkIn, int availableRooms) {
        this.hotelName = hotelName;
        this.hotelID = hotelID;
        this.stars = stars;
        this.hotelAddress = hotelAddress;
        this.dailyPrice = dailyPrice;
        this.city = city;
        this.county = county;
        this.availableRooms = availableRooms;
    }

    public int getHotelID() {
        return hotelID;
    }

    public String getHotelName() {
        return hotelName;
    }
}
