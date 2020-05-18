package sample.Model;

import javafx.beans.property.SimpleStringProperty;

public class AdminPackageTable {

    private SimpleStringProperty packageName, departureDate,returnDate, departureCity, arrivalCity, flightName,
            hotelName, numOfNights, typeOfRoom, carType, price, flightID;

    public AdminPackageTable(String flightID, String packageName, String departureDate, String returnDate, String departureCity,
                             String arrivalCity, String flightName, String hotelName, String numOfNights,
                             String typeOfRoom, String carType, String price) {
        this.flightID = new SimpleStringProperty(flightID);
        this.packageName = new SimpleStringProperty(packageName);
        this.departureDate = new SimpleStringProperty(departureDate);
        this.returnDate= new SimpleStringProperty(returnDate);
        this.departureCity = new SimpleStringProperty(departureCity);
        this.arrivalCity = new SimpleStringProperty(arrivalCity);
        this.flightName = new SimpleStringProperty(flightName);
        this.hotelName = new SimpleStringProperty(hotelName);
        this.numOfNights = new SimpleStringProperty(numOfNights);
        this.typeOfRoom = new SimpleStringProperty(typeOfRoom);
        this.carType = new SimpleStringProperty(carType);
        this.price = new SimpleStringProperty(price);
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

    public String getDepartureDate() {
        return departureDate.get();
    }

    public SimpleStringProperty departureDateProperty() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate.set(departureDate);
    }

    public String getReturnDate() {
        return returnDate.get();
    }

    public SimpleStringProperty returnDateProperty() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate.set(returnDate);
    }

    public String getDepartureCity() {
        return departureCity.get();
    }

    public SimpleStringProperty departureCityProperty() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity.set(departureCity);
    }

    public String getArrivalCity() {
        return arrivalCity.get();
    }

    public SimpleStringProperty arrivalCityProperty() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity.set(arrivalCity);
    }

    public String getFlightName() {
        return flightName.get();
    }

    public SimpleStringProperty flightNameProperty() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName.set(flightName);
    }

    public String getHotelName() {
        return hotelName.get();
    }

    public SimpleStringProperty hotelNameProperty() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName.set(hotelName);
    }

    public String getNumOfNights() {
        return numOfNights.get();
    }

    public SimpleStringProperty numOfNightsProperty() {
        return numOfNights;
    }

    public void setNumOfNights(String numOfNights) {
        this.numOfNights.set(numOfNights);
    }

    public String getTypeOfRoom() {
        return typeOfRoom.get();
    }

    public SimpleStringProperty typeOfRoomProperty() {
        return typeOfRoom;
    }

    public void setTypeOfRoom(String typeOfRoom) {
        this.typeOfRoom.set(typeOfRoom);
    }

    public String getCarType() {
        return carType.get();
    }

    public SimpleStringProperty carTypeProperty() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType.set(carType);
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

    public String getFlightID() {
        return flightID.get();
    }

    public SimpleStringProperty flightIDProperty() {
        return flightID;
    }

    public void setFlightID(String flightID) {
        this.flightID.set(flightID);
    }
}
