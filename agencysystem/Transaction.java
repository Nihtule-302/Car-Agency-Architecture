package agencysystem;

import identification.Cars.Car;
import identification.Customers.Customer;
import identification.Employees.Employee;
import java.util.ArrayList;
import java.util.Stack;

public class Transaction {
    public static double Income = 0;
    public static ArrayList<Transaction> transactions = new ArrayList<>();
    public static Stack<Transaction> undoStack = new Stack<>();

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

        // Save transaction for undo
        transactions.add(this);
        undoStack.push(this); // Push the transaction onto the stack for undo functionality
    }

    public void rent(Car car) {
        this.paymentType = "rent";
        this.car = car;
        employee.increasePayCheck((car.getRent() * 0.01));
        manager.removeCar(car.getName());
        Income += car.getRent();
        price = car.getRent();

        // Save transaction for undo
        transactions.add(this);
        undoStack.push(this); // Push the transaction onto the stack for undo functionality
    }

    // Undo the most recent transaction
    public static void undoTransaction() {
        if (!undoStack.isEmpty()) {
            Transaction lastTransaction = undoStack.peek();
            undoStack.pop(); // Pop the last transaction
            Car car = lastTransaction.car;
            Employee employee = lastTransaction.employee;

            // Revert the actions made by the transaction
            Admin manager = Admin.getInstance();
            manager.addCar(car);

            // Optionally, revert the income update as well
            if ("cash".equals(lastTransaction.paymentType)) {
                Income -= car.getPrice();
                employee.decreasePayCheck(car.getPrice());
            } else if ("rent".equals(lastTransaction.paymentType)) {
                Income -= car.getRent();
                employee.decreasePayCheck(car.getRent());
            }

            System.out.println("Transaction undone.");
        } else {
            System.out.println("No transaction to undo.");
        }
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
