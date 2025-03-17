package factory;

import identification.Car;
import identification.Economy;
import identification.Regular;
import identification.Premiuim;
import identification.Vip;

public class CarFactory {
    public static Car createCustomer(String name,CarType carType) {
        switch (carType) {
            case ECONOMY:
                return new Economy(name);

            case LUXURY:
                return new Luxury(name);

            case SPORTS:
                return new Sports(name);

             default:
                throw new IllegalArgumentException("Invalid employee type: " + carType);
        }
    }
}
