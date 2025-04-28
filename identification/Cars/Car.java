package identification.Cars;

import identification.Identification;

public class Car extends Identification {
    private double price;
    private double rent;

    // No-arg constructor
    public Car() {
    }

    // Constructor with model, price, and rent parameters
    public Car(String model, double price, double rent) {
        setName(model);
        this.price = price;
        this.rent = rent;
    }

    // Getter for Price
    public double getPrice() {
        return price;
    }

    // Getter for Rent
    public double getRent() {
        return rent;
    }
}
