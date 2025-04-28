package StrategyCarSorting;

import identification.Cars.Car;
import java.util.List;

public class CarSorter {
    private CarSortStrategy strategy;

    public void setStrategy(CarSortStrategy strategy) {
        this.strategy = strategy;
    }

    public void sortCars(List<Car> cars) {
        if (strategy != null) {
            strategy.sort(cars);
        }
    }
}
