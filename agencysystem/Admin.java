package agencysystem;

import java.util.ArrayList;
import java.util.List;
import EntityTypes.CarType;
import EntityTypes.CustomerType;
import EntityTypes.EmployeeType;
import FactoryandBuilder.*;
import identification.Cars.Car;
import identification.Customers.Customer;
import identification.Employees.Employee;

public class Admin {
    private List<Employee> employees = new ArrayList<>();
    private static List<Customer> customers = new ArrayList<>();
    private static List<Car> cars = new ArrayList<>();
    private static Admin instance;

    private Admin() {
        // Use builder to create and add default employees
        employees.add(new EmployeeBuilder().setName("Thorfinn")
                .setEmployeeType(EmployeeType.MANAGER).build());
        employees.get(0).setId(1); // Manually set ID for Thorfinn

        employees.add(new EmployeeBuilder().setName("Musashi").setEmployeeType(EmployeeType.NORMAL)
                .build());
        employees.get(1).setId(2); // Manually set ID for Musashi

        employees.add(
                new EmployeeBuilder().setName("Fushi").setEmployeeType(EmployeeType.HR).build());
        employees.get(2).setId(3); // Manually set ID for Fushi


        // Use builder to create and add default cars
        cars.add(new CarBuilder().setModel("BMW").setPrice(1232000).setRent(1755)
                .setCarType(CarType.ECONOMY).build());
        cars.get(0).setId(1); // Manually set ID for BMW

        cars.add(new CarBuilder().setModel("MERCEDES").setPrice(1848000).setRent(2156)
                .setCarType(CarType.LUXURY).build());
        cars.get(1).setId(2); // Manually set ID for MERCEDES

    }

    public static Admin getInstance() {
        if (instance == null) {
            instance = new Admin();
        }
        return instance;
    }

    public void addEmployee(String name, EmployeeType empt) {
        Employee newEmployee = new EmployeeBuilder().setName(name).setEmployeeType(empt).build();
        newEmployee.setId(employees.size() + 1); // Assign the ID based on the current size (index)
        employees.add(newEmployee);
    }

    public void addCustomer(String name, CustomerType cmpt) {
        Customer newCustomer = new CustomerBuilder().setName(name).setCustomerType(cmpt).build();
        newCustomer.setId(customers.size() + 1); // Assign the ID based on the current size (index)
        customers.add(newCustomer);
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void removeEmployee(String name) {
        employees.removeIf(employee -> employee.getName().equals(name));
    }

    public void removeCustomer(String name) {
        customers.removeIf(customer -> customer.getName().equals(name));
    }

    public void removeCar(String model) {
        cars.removeIf(car -> car.getName().equals(model));
    }

    public Car getCar(String model) {
        return cars.stream().filter(car -> car.getName().equals(model)).findFirst().orElse(null);
    }

    public Car getCar(int id) {
        return cars.stream().filter(car -> car.getId() == id).findFirst().orElse(null);
    }

    public List<Car> getCars() {
        return cars;
    }

    public Employee getEmployee(String name) {
        return employees.stream().filter(employee -> employee.getName().equals(name)).findFirst()
                .orElse(null);
    }

    public Employee getEmployee(int id) {
        return employees.stream().filter(employee -> employee.getId() == id).findFirst()
                .orElse(null);
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public Customer getCustomer(String name) {
        return customers.stream().filter(customer -> customer.getName().equals(name)).findFirst()
                .orElse(null);
    }

    public Customer getCustomer(int id) {
        return customers.stream().filter(customer -> customer.getId() == id).findFirst()
                .orElse(null);
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}
