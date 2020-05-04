package Model;

import java.util.Date;

public class Flight {
    private int flight_Id;
    private String origin;
    private String destination;
    private Date arrival_date;
    private Date departure_date;
    public double price;
    private String stops;
    private String direct_trip;
    private String round_trip;


    public Flight(int flight_Id, String origin, String destination, Date arrival_date, Date departure_date, double price) {
        this.flight_Id = flight_Id;
        this.origin = origin;
        this.destination=destination;
        this.departure_date = departure_date;
        this.arrival_date=arrival_date;
        this.price=price;
    }

    public int getFlight_Id() {
        return flight_Id;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public Date getArrival_time() {
        return arrival_date;
    }

    public Date getDeparture_time() {
        return departure_date;
    }

    public String getStops() {
        return stops;
    }

    public String getDirect_trip() {
        return direct_trip;
    }

    public String getRound_trip() {
        return round_trip;
    }

    public double getPrice() {
        return price;
    }
}
