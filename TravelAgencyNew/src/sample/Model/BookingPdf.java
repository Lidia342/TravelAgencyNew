package sample.Model;

public class BookingPdf {
    String bookingID, name, packageName, bookingDate, price, departureDate, returnDate, departureCity, arrivalCity,
            flightName, hotelName, numOfNights, typeOfRoom, carType;

    public BookingPdf(String bookingID, String name, String packageName, String bookingDate, String price, String departureDate,
                      String returnDate, String departureCity, String arrivalCity, String flightName, String hotelName, String numOfNights,
                      String typeOfRoom, String carType) {
        this.bookingID = bookingID;
        this.name = name;
        this.packageName = packageName;
        this.bookingDate = bookingDate;
        this.price = price;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.flightName = flightName;
        this.hotelName = hotelName;
        this.numOfNights = numOfNights;
        this.typeOfRoom = typeOfRoom;
        this.carType = carType;
    }

    public BookingPdf() {

    }

    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getNumOfNights() {
        return numOfNights;
    }

    public void setNumOfNights(String numOfNights) {
        this.numOfNights = numOfNights;
    }

    public String getTypeOfRoom() {
        return typeOfRoom;
    }

    public void setTypeOfRoom(String typeOfRoom) {
        this.typeOfRoom = typeOfRoom;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingID='" + bookingID + '\'' +
                ", name='" + name + '\'' +
                ", packageName='" + packageName + '\'' +
                ", bookingDate='" + bookingDate + '\'' +
                ", price='" + price + '\'' +
                ", departureDate='" + departureDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", departureCity='" + departureCity + '\'' +
                ", arrivalCity='" + arrivalCity + '\'' +
                ", flightName='" + flightName + '\'' +
                ", hotelName='" + hotelName + '\'' +
                ", numOfNights='" + numOfNights + '\'' +
                ", typeOfRoom='" + typeOfRoom + '\'' +
                ", carType='" + carType + '\'' +
                '}';
    }
}
