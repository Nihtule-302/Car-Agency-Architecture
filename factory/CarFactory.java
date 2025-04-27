package factory;

import EntityTypes.CarType;
import identification.Cars.Car;
import identification.Cars.Economy;
import identification.Cars.Luxury;
import identification.Cars.Sports;

public class CarFactory {
    private String model;
    private double price;
    private double rent;
    private CarType carType;


    public static Car createCar(CarBuilder car) {
        return switch (car.carType) {
            case ECONOMY -> new Economy(car.model, car.price, car.rent);
            case LUXURY -> new Luxury(car.model, car.price, car.rent);
            case SPORTS -> new Sports(car.model, car.price, car.rent);
            default -> throw new IllegalArgumentException("Invalid employee type: " + car.carType);
        };
    }
}

