package FactoryandBuilder;

import EntityTypes.CarType;
import identification.Cars.Car;

public class CarBuilder {
    private String model;
    private double price;
    private double rent;
    private CarType carType;

    public CarBuilder setModel(String model) {
        this.model = model;
        return this;
    }

    public CarBuilder setPrice(double price) {
        this.price = price;
        return this;
    }

    public CarBuilder setRent(double rent) {
        this.rent = rent;
        return this;
    }

    public CarBuilder setCarType(CarType carType) {
        this.carType = carType;
        return this;
    }

    public Car build() {
        return CarFactory.createCar(carType, model, price, rent);
    }
}
