package sample.Model;

import javafx.beans.property.SimpleStringProperty;

public class PersonTable {
    private SimpleStringProperty SSN, firstName, lastName, phoneNumber, email, password, address;

    public PersonTable(String SSN, String firstName, String lastName,
                     String phoneNumber, String email, String password,
                     String address) {
        this.SSN = new SimpleStringProperty(SSN);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(password);
        this.address = new SimpleStringProperty(address);
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

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public SimpleStringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }
}
