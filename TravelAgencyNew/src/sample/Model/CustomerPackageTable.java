package sample.Model;

public class CustomerPackageTable {
    private String packageName;
    private String departureDate;
    private String returnDate;
    private String departureCity;
    private String arrivalCity;
    private String flightName;
    private String hotelName;
    private String roomType;
    private String nights;
    private String carType;
    private String price;


    public CustomerPackageTable(String packageName, String departureDate, String returnDate, String departureCity, String arrivalCity,
                                String flightName, String hotelName, String roomType, String nights, String carType, String price) {
        this.packageName = packageName;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.flightName = flightName;
        this.hotelName = hotelName;
        this.roomType = roomType;
        this.nights = nights;
        this.carType = carType;
        this.price = price;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
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

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getNights() {
        return nights;
    }

    public void setNights(String nights) {
        this.nights = nights;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

