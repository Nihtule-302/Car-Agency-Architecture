package StrategyCarSorting;

import identification.Cars.Car;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortByName implements CarSortStrategy {
    @Override
    public void sort(List<Car> cars) {
        Collections.sort(cars, Comparator.comparing(Car::getName));
    }
}
