package agencysystem;

import identification.Cars.Car;
import identification.Customers.Customer;
import identification.Employees.Employee;
import java.util.ArrayList;

public class Transaction {
    private static double Income = 0;
    private static ArrayList<Transaction> transactions = new ArrayList<>();

    private Employee employee;
    private Customer customer;
    private Car car;
    private String paymentType;
    private double price;
    private Admin manager = Admin.getInstance();

    public void setEmployeeAndCustomer(Employee employee, Customer customer) {
        this.employee = employee;
        this.customer = customer;
    }

    public void buy(Car car) {
        this.paymentType = "cash";
        this.car = car;
        employee.increasePayCheck((car.getPrice() * 0.01));
        manager.removeCar(car.getName());
        Income += car.getPrice();
        price = car.getPrice();
    }

    public void rent(Car car) {
        this.paymentType = "rent";
        this.car = car;
        employee.increasePayCheck((car.getRent() * 0.01));
        manager.removeCar(car.getName());
        Income += car.getRent();
        price = car.getRent();
    }

    public void saveTransaction() {
        transactions.add(this);
    }

    public double getIncome() {
        return Income;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public String getEmployeeName() {
        return employee.getName();
    }

    public String getCarModel() {
        return car.getName();
    }

    public double getPrice() {
        return price;
    }

    public String getCustomerName() {
        return customer.getName();
    }
}
