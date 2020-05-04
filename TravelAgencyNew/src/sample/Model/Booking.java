package sample.Model;

import java.util.Date;

public class Booking {

    private Date bookingDate;
    private int customerID ;
    private double bookingTotalPrice;
    private boolean bookingIsPaid;
    public Flight flight;
    public Hotel hotel;
    public Car car;

       //ArrayList of flights
       //ArrayList of hotels
       //ArrayList of cars


    public Booking(Date bookingDate, int customerID, double bookingTotalPrice, boolean bookingIsPaid, Flight flight, Hotel hotel, Car car) {
        this.bookingDate = bookingDate;
        this.customerID = customerID;
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

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
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
