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


    public Package getPackage1() {
        return package1;
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

}