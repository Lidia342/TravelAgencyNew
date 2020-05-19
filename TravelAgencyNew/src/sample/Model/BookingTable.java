package sample.Model;

import javafx.beans.property.SimpleStringProperty;

public class BookingTable {
    private SimpleStringProperty bookingId,SSN, firstName, lastName, packageName, date, price;


    public BookingTable(String bookingId,String ssn, String firstName, String lastName, String packageName,
                        String date, String price) {
        this.bookingId = new SimpleStringProperty(bookingId);
        this.SSN = new SimpleStringProperty(ssn);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.packageName = new SimpleStringProperty(packageName);
        this.date = new SimpleStringProperty(date);
        this.price = new SimpleStringProperty(price);
    }

    public String getBookingId() {
        return bookingId.get();
    }

    public SimpleStringProperty bookingIdProperty() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId.set(bookingId);
    }

    public String getSSN() {
        return SSN.get();
    }

    public SimpleStringProperty SSNProperty() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN.set(SSN);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getPackageName() {
        return packageName.get();
    }

    public SimpleStringProperty packageNameProperty() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName.set(packageName);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getPrice() {
        return price.get();
    }

    public SimpleStringProperty priceProperty() {
        return price;
    }

    public void setPrice(String price) {
        this.price.set(price);
    }
}
