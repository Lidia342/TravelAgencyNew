package Model;

import java.util.Date;

public class Hotel {

    public String hotelName;
    private int hotelID;
    public int stars;
    public String hotelAddress;
    public double dailyPrice;
    public String city;
    public String county;
    public Date checkIn;
    public int availableRooms;

    public int getHotelID() {
        return hotelID;
    }
}
