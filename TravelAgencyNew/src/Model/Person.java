package Model;

public abstract class Person {

    private String firstName;
    private int ID;
    private String lastName;
    private String phoneNumber;
    private String SSN;
    private String email;
    private String password;
    private String address;
    private String street;
    private String city;
    private int postCode;
    private String userName;

    public Person(int ID,String firstName, String lastName, String userName, String password, String SSN, String email, String phoneNumber, String street, String city, int postCode) {

        this.ID=ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName=userName;
        this.phoneNumber = phoneNumber;
        this.SSN = SSN;
        this.email=email;
        this.password = password;
        this.address = address;
        this.street = street;
        this.city = city;
        this.postCode = postCode;
    }
}
