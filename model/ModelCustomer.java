package model;

public class ModelCustomer {
    private int id;
    private String FirstName;
    private String LastName;
    private String Licence;
    private String Address;
    private int Phone;
    private int Type;
    private int password;

    // Constructeur
    public ModelCustomer(String FirstName, String LastName, String Licence, String Address,
                        int Phone, int Type, int password) {
        this.id = id;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Licence = Licence;
        this.Address = Address;
        this.Phone = Phone;
        this.Type = Type;
        this.password = password;
    }

    // Getters et Setters (pour chaque attribut)
// Getters et Setters pour chaque attribut

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getLicence() {
        return Licence;
    }

    public String getAddress() {
        return Address;
    }

    public int getPhone() {
        return Phone;
    }

    public int getType() {
        return Type;
    }

    public int getPassword() {
        return password;
    }

}
