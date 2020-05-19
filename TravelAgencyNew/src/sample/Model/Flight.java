package sample.Model;

import java.util.Date;

public class Flight {
    public Date return_date;
    int flightID;
    private String origin;
    private String destination;
    private Date departure_date;
    public double price;

    public Flight(int flightID, Date departure_date, Date return_date, String origin, String destination, double price) {
        this.flightID=flightID;
        this.origin = origin;
        this.destination=destination;
        this.departure_date = departure_date;
        this.price=price;
        this.return_date=return_date;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public Date getDeparture_time() {
        return departure_date;
    }

    public int getFlightID() {
        return flightID;
    }

    public double getPrice() {
        return price;
    }

    public Date getReturnDate() {
        return return_date;
    }
}
