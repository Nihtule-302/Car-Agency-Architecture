package utility;

import agencysystem.Admin;
import agencysystem.Transaction;
import identification.Cars.Car;
import identification.Customers.Customer;
import identification.Employees.Employee;

import java.util.InputMismatchException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import EntityTypes.CustomerType;

public class Talk {
    private Customer customer;
    private Car car;
    private Admin manager = Admin.getInstance(); // Singleton access
    private List<Car> cars = new ArrayList<>(manager.getCars());
    private List<Employee> employees = new ArrayList<>(manager.getEmployees());
    private Transaction transaction = new Transaction();
    Scanner input = new Scanner(System.in);
    private Employee employee;
    private String paymentType;
    private int choice;

    private Employee getRandomEmployee() {
        if (employees.isEmpty()) {
            System.out.println(
                    "⚠️ No employees available at the moment. Please contact an administrator.");
            return null;
        }
        int randomIndex = (int) (Math.random() * employees.size());
        return employees.get(randomIndex);
    }

    public void greetings() {
        employee = getRandomEmployee();
        if (employee == null) {
            System.out.println(
                    "⚠️ No employees available at the moment. Please contact an administrator.");
            return;
        }

        System.out.println("Hello, my name is " + employee.getName());
        System.out.println("Please enter your name: ");
        String name = input.next();

        // Ask the user for the customer type
        System.out.println("Choose Customer Type: (1) Regular | (2) VIP");
        int customerTypeChoice = input.nextInt();
        CustomerType customerType = CustomerType.REGULAR; // Default value
        switch (customerTypeChoice) {
            case 1:
                customerType = CustomerType.REGULAR;
                break;
            case 2:
                customerType = CustomerType.VIP;
                break;
            default:
                System.out.println("Invalid choice, setting default to Regular.");
        }// Pass customerType to the constructor
        manager.addCustomer(name, customerType);
        customer = manager.getCustomer(name);
        transaction.setEmployeeAndCustomer(employee, customer);
    }


    public void howCanIHelp() {
        System.out.println("Would you like to buy or  rent?");
        System.out.println("0: rent, 1: buy");
        boolean flag1 = true;
        while (flag1) {
            try {
                choice = input.nextInt();
                if (choice != 0 && choice != 1) {
                    System.out.println("Please try again. 0: rent, 1: buy");
                } else {
                    flag1 = false;
                }
            } catch (InputMismatchException ime) {
                System.out.println("Please enter a valid number");
                input.nextLine(); // ✅ Fix
            }
        }
        switch (choice) {
            case 0:
                paymentType = "rent";
                break;
            case 1:
                paymentType = "buy";
                break;
        }

        System.out.println("Choose your car from the available list (ID):");
        System.out.println("Name | ID | Price | rent");
        for (Car car : cars) {
            if (car != null)
                System.out.println(car.getName() + " | " + car.getId() + " | " + car.getPrice()
                        + " | " + car.getRent());
        }

        boolean flag2 = true;
        while (flag2) {
            try {
                int carID = input.nextInt();
                car = manager.getCar(carID);
                flag2 = false;
            } catch (Exception e) {
                System.out.println("Please enter a valid ID");
                input.nextLine();
            }
        }

        switch (paymentType) {
            case "rent":
                transaction.rent(car);
                break;
            case "buy":
                transaction.buy(car);
                break;
            default:
        }

        transaction.saveTransaction();

        thatWillBe(employee, customer, car, paymentType);
    }

    private void thatWillBe(Employee employee, Customer customer, Car car, String paymentType) {
        System.out.println("Thank you, " + customer.getName());
        if (paymentType.equals("buy"))
            System.out.println("Your total will be: " + car.getPrice());
        if (paymentType.equals("rent"))
            System.out.println("Your total will be: " + car.getRent());
    }
}
