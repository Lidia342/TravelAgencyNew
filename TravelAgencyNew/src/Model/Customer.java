package Model;

public class Customer extends Person {

    private String cvcNumber;
    private String cardNumber;
    private int expirationMonth;
    private int expirationYear;

    public Customer(int id, String firstName, String lastName, String userName, String password,
                    String email, String SSN, String mobile, String street, String city, int postCode,
                    String cvcNumber,String cardNumber, int expirationMonth,int expirationYear) {
        super(id, firstName, lastName, userName, password, SSN, email, mobile, street, city, postCode);
        this.cvcNumber=cvcNumber;
        this.cardNumber=cardNumber;
        this.expirationMonth=expirationMonth;
        this.expirationYear=expirationYear;
    }

    public String getCvcNumber() {
        return cvcNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public int getExpirationMonth() {
        return expirationMonth;
    }

    public int getExpirationYear() {
        return expirationYear;
    }
}

