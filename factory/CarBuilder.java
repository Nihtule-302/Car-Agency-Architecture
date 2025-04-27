package factory;

import EntityTypes.CarType;
import identification.Cars.Car;

public class CarBuilder {
    String model;
    double price;
    double rent;
    CarType carType;

    public CarBuilder setModel(String model)
    {
        this.model = model;
        return this;
    }

    public CarBuilder setPrice(double price)
    {
        this.price = price;
        return this;
    }

    public CarBuilder setRent(double rent)
    {
        this.rent = rent;
        return this;
    }

    public CarBuilder setCarType(CarType carType)
    {
        this.carType = carType;
        return this;
    }

    public Car build()
    {
        return CarFactory.createCar(this);
    }
}
