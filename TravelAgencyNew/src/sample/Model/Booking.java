package sample.Model;

import java.util.Date;

public class Booking {

    private Date bookingDate;
    private String customerID ;
    private double bookingTotalPrice;
    private boolean bookingIsPaid;
    public Flight flight;
    public Hotel hotel;
    public Car car;

       //ArrayList of flights
       //ArrayList of hotels
       //ArrayList of cars


    public Booking(Date bookingDate, String customerID, boolean bookingIsPaid, double bookingTotalPrice, Flight flight, Hotel hotel, Car car) {
        this.bookingDate = bookingDate;
        this.customerID = customerID;
        this.bookingIsPaid=bookingIsPaid;
        this.bookingTotalPrice = bookingTotalPrice;
        this.flight=flight;
        this.hotel=hotel;
        this.car=car;

    }


    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public double getBookingTotalPrice() {
        return bookingTotalPrice;
    }

    public void setBookingTotalPrice(double bookingTotalPrice) {
        this.bookingTotalPrice = bookingTotalPrice;
    }

    public boolean isBookingIsPaid() {
        return bookingIsPaid;
    }

    public void setBookingIsPaid(boolean bookingIsPaid) {
        this.bookingIsPaid = bookingIsPaid;

    }
}