package FactoryandBuilder;

import EntityTypes.CarType;
import identification.Cars.Car;
import identification.Cars.Economy;
import identification.Cars.Luxury;
import identification.Cars.Sports;

public class CarFactory {
    public static Car createCar(CarType carType, String model, double price, double rent) {
        switch (carType) {
            case ECONOMY -> {
                return new Economy(model, price, rent);
            }
            case LUXURY -> {
                return new Luxury(model, price, rent);
            }
            case SPORTS -> {
                return new Sports(model, price, rent);
            }
            default -> throw new IllegalArgumentException("Invalid car type: " + carType);
        }
    }
}
