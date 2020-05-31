package sample.Model;

import javafx.beans.property.SimpleStringProperty;

public class UserTable {
    private SimpleStringProperty SSN, firstName, lastName, phoneNumber, email, password, address, type;

    public UserTable(String SSN, String firstName, String lastName,
                     String phoneNumber, String email, String password,
                     String address, String type) {
        this.SSN = new SimpleStringProperty(SSN);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(password);
        this.address = new SimpleStringProperty(address);
        this.type = new SimpleStringProperty(type);
    }

    public String getSSN() {
        return SSN.get();
    }

    public void setSSN(String SSN) {
        this.SSN.set(SSN);
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty SSNProperty() {
        return SSN;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public SimpleStringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }
}
