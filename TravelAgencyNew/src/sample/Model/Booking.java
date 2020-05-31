package sample.Model;

import java.util.Date;

public class Booking {

    private Package package1;
    private Date bookingDate;
    private String customerID ;

    public Booking(Date bookingDate, String customerID, Package package1) {
        this.bookingDate = bookingDate;
        this.customerID = customerID;
        this.package1=package1;
    }


    public Date getBookingDate() {
        return bookingDate;
    }



}