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


    public static Car createCar(String model, double price, double rent, CarType carType) {
        return switch (carType) {
            case ECONOMY -> new Economy(model, price, rent);
            case LUXURY -> new Luxury(model, price, rent);
            case SPORTS -> new Sports(model, price, rent);
            default -> throw new IllegalArgumentException("Invalid employee type: " + carType);
        };
    }

    // 🔹 Builder for CarFactory
    public static class Builder {
        private final CarFactory factoryInstance;

        public Builder() {
            this.factoryInstance = new CarFactory();
        }

        public Builder setModel(String model) {
            factoryInstance.model = model;
            return this;
        }

        public Builder setPrice(double price) {
            factoryInstance.price = price;
            return this;
        }

        public Builder setRent(double rent) {
            factoryInstance.rent = rent;
            return this;
        }

        public Builder setCarType(CarType carType) {
            factoryInstance.carType = carType;
            return this;
        }

        public Car build() {
            return createCar(factoryInstance.model, factoryInstance.price, factoryInstance.rent, factoryInstance.carType);
        }
    }
}
