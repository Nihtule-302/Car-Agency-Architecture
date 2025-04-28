package StrategyCarSorting;

import identification.Cars.Car;
import java.util.List;

public interface CarSortStrategy {
    void sort(List<Car> cars);
}
