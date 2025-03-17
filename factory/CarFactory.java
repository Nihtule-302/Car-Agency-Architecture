package factory;

import identification.*;

public class CarFactory {
    public static Car createCar(String model,double price,double rent,CarType carType) {
        switch (carType) {
            case ECONOMY:
                return new Economy(model, price, rent);

            case LUXURY:
                return new Luxury(model, price, rent);

            case SPORTS:
                return new Sports(model, price, rent);

             default:
                throw new IllegalArgumentException("Invalid employee type: " + carType);
        }
    }
}
