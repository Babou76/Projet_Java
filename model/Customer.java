package model;

public class Customer {
    private String password;
    private String Phone;
    private int Type;
    private String Licence;
    private String Address;
    private String LastName;
    private String FirstName;

    // Constructeurs
    public Customer(String FirstName, String LastName, String Licence, int Type, String Address,
                    String Phone, String password) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Licence = Licence;
        this.Type = Type;
        this.Address = Address;
        this.Phone = Phone;
        this.password = password;
    }

    // Getters
    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getLicence() {
        return Licence;
    }

    public int getType() {
        return Type;
    }

    public String  getAddress() {
        return Address;
    }

    public String getPhone() {
        return Phone;
    }

    public String getpassword() {
        return password;
    }

}