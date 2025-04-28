package StrategyCarSorting;

import identification.Cars.Car;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortByPrice implements CarSortStrategy {
    @Override
    public void sort(List<Car> cars) {
        Collections.sort(cars, Comparator.comparingDouble(Car::getPrice));
    }
}
