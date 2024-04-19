package model;
import java.util.Date;

public class Car {
    private String brand;
    private String name;
    private Date date;
    private String category;
    private double pricePerDay;
    private int numberOfSeats;
    private String transmission;
    private String specification;
    private String description;

    // Constructeurs
    public Car(String brand, String name, Date date, String category, double pricePerDay,
               int numberOfSeats, String transmission, String specification, String description) {
        this.brand = brand;
        this.name = name;
        this.date = date;
        this.category = category;
        this.pricePerDay = pricePerDay;
        this.numberOfSeats = numberOfSeats;
        this.transmission = transmission;
        this.specification = specification;
        this.description = description;
    }

    // Getters
    public String getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public String getTransmission() {
        return transmission;
    }

    public String getSpecification() {
        return specification;
    }

    public String getDescription() {
        return description;
    }
}