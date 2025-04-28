package identification.Cars;

import EntityTypes.CarType;
import identification.Identification;

public class Car extends Identification {
    private double price;
    private double rent;
    private CarType carType;
    public static int CntId = 0;


    // No-arg constructor
    public Car() {
        CntId++;
        this.setId(CntId);
    }

    // Constructor with model, price, and rent parameters
    public Car(String model, double price, double rent) {
        setName(model);
        this.price = price;
        this.rent = rent;
        CntId++;
        this.setId(CntId);
    }

    // Getter for Price
    public double getPrice() {
        return price;
    }

    // Getter for Rent
    public double getRent() {
        return rent;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

}
