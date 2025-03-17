package agencysystem;

import factory.CarFactory;
import factory.CustomerFactory;
import factory.CustomerType;
import factory.EmployeeFactory;
import factory.EmployeeType;
import identification.Employee;
import identification.Car;
import identification.Customer;

public class Admin {
    private Employee[] employees = defaultEmployees();
    private Customer[] customers = new Customer[3];
    private Car[] cars = defaultCars();

    static Admin admin = null;

    private Admin() { }

    public static Admin getInstance() {
        if (admin == null)
            admin = new Admin();
        return admin;
    }

    private static Employee[] defaultEmployees() {
        Employee[] employees = new Employee[3];
        employees[0] = EmployeeFactory.createEmployee("Thorfinn",EmployeeType.MANAGER);
        employees[1] = EmployeeFactory.createEmployee("Musashi",EmployeeType.NORMAL);
        employees[2] = EmployeeFactory.createEmployee("Fushi",EmployeeType.HR);
        return employees;
    }

    private static Car[] defaultCars() {
        Car[] cars = new Car[2];
        cars[0] = CarFactory.createCar("BMW", 1232000, 1755);
        cars[1] = CarFactory.createCar("MERCEDES", 1848000, 2156);
        return cars;
    }

    private void resizeArray(Object[] array) {
        if (array instanceof Employee[]) {
            Employee[] temp = new Employee[array.length + 1];
            System.arraycopy(array, 0, temp, 0, array.length);
            employees = temp;
        } else if (array instanceof Customer[]) {
            Customer[] temp = new Customer[array.length + 1];
            System.arraycopy(array, 0, temp, 0, array.length);
            customers = temp;
        } else if (array instanceof Car[]) {
            Car[] temp = new Car[array.length + 1];
            System.arraycopy(array, 0, temp, 0, array.length);
            cars = temp;
        }
    }

    private int findAvailableIndex(Object[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                return i;
            }
        }

        resizeArray(arr);

        return arr.length - 1;
    }

    public void addEmployee(String name,EmployeeType empt) {
        Employee newEmployee = EmployeeFactory.createEmployee(name,empt);
        int index = findAvailableIndex(employees);
        employees[index] = newEmployee;
    }

    public void addCustomer(String name,CustomerType cmpt) {
        Customer newCustomer = CustomerFactory.createCustomer(name,cmpt);
        int index = findAvailableIndex(customers);
        customers[index] = newCustomer;
    }

    public void addCustomer(Customer customer) {
        int index = findAvailableIndex(customers);
        customers[index] = customer;
    }

    public void addCar(String model, double price, double rent) {
        Car newCar = CarFactory.createCar(model, price, rent);
        int index = findAvailableIndex(cars);
        cars[index] = newCar;
    }

    public void removeEmployee(String name) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].getName().equals(name)) {
                employees[i] = null;
                break;
            }
        }
    }

    public void removeCustomer(String name) {
        for (int i = 0; i < customers.length; i++) {
            if (customers[i] != null && customers[i].getName().equals(name)) {
                customers[i] = null;
                break;
            }
        }
    }

    public void removeCar(String model) {
        for (int i = 0; i < cars.length; i++) {
            if (cars[i] != null && cars[i].getName().equals(model)) {
                cars[i] = null;
                break;
            }
        }
    }

    public Car getCar(String model) {
        for (Car car : cars) {
            if (car != null && car.getName().equals(model)) {
                return car;
            }
        }
        return null;
    }

    public Car getCar(int id) {
        for (Car car : cars) {
            if (car != null && car.getId() == id) {
                return car;
            }
        }
        return null;
    }

    public Car[] getCars() {
        return cars;
    }

    public Employee getEmployee(String name) {
        for (Employee employee : employees) {
            if (employee != null && employee.getName().equals(name)) {
                return employee;
            }
        }
        return null;
    }

    public Employee getEmployee(int id) {
        for (Employee employee : employees) {
            if (employee != null && employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }

    public Employee[] getEmployees() {
        return employees;
    }

    public Customer getCustomer(String name) {
        for (Customer customer : customers) {
            if (customer != null && customer.getName().equals(name)) {
                return customer;
            }
        }
        return null;
    }

    public Customer getCustomer(int id) {
        for (Customer customer : customers) {
            if (customer != null && customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }

    public Customer[] getCustomers() {
        return customers;
    }
}
