package FactoryandBuilder;

import EntityTypes.CarType;
import identification.Cars.Car;
import identification.Cars.Economy;
import identification.Cars.Luxury;
import identification.Cars.Sports;

public class CarFactory {
    public static Car createCar(CarType carType, String model, double price, double rent) {
        Car car;
        switch (carType) {
            case LUXURY:
                car = new Luxury(model, price, rent);
                break;
            case ECONOMY:
                car = new Economy(model, price, rent);
                break;
            case SPORTS:
                car = new Sports(model, price, rent);
                break;
            default:
                car = new Car(model, price, rent);
        }
        car.setCarType(carType); // 🔥 Important! Set the type inside the car
        return car;
    }

}
