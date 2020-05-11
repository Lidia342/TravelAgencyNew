package sample.Model;

import javafx.beans.property.SimpleStringProperty;

public class packageTable {

    private SimpleStringProperty packageName, flightDepartureDate, departing_city, arriving_city, flightName,
            hotelName, numOfNights, typeOfRoom, carType, price;

    public packageTable(String packageName, String flightDepartureDate, String departing_city, String arriving_city,
                        String flightName, String hotelName,
                        String numOfNights, String typeOfRoom,
                        String carType, String price) {
        this.packageName = new SimpleStringProperty(packageName);
        this.flightDepartureDate = new SimpleStringProperty(flightDepartureDate);
        this.departing_city = new SimpleStringProperty(departing_city);
        this.arriving_city = new SimpleStringProperty(arriving_city);
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

    public String getFlightDepartureDate() {
        return flightDepartureDate.get();
    }

    public SimpleStringProperty flightDepartureDateProperty() {
        return flightDepartureDate;
    }

    public void setFlightDepartureDate(String flightDepartureDate) {
        this.flightDepartureDate.set(flightDepartureDate);
    }

    public String getDeparting_city() {
        return departing_city.get();
    }

    public SimpleStringProperty departing_cityProperty() {
        return departing_city;
    }

    public void setDeparting_city(String departing_city) {
        this.departing_city.set(departing_city);
    }

    public String getArriving_city() {
        return arriving_city.get();
    }

    public SimpleStringProperty arriving_cityProperty() {
        return arriving_city;
    }

    public void setArriving_city(String arriving_city) {
        this.arriving_city.set(arriving_city);
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
}

