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
import StrategyCarSorting.CarSorter;
import StrategyCarSorting.SortById;
import StrategyCarSorting.SortByName;
import StrategyCarSorting.SortByPrice;

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
        }
        // Pass customerType to the constructor
        manager.addCustomer(name, customerType);
        customer = manager.getCustomer(name);
        transaction.setEmployeeAndCustomer(employee, customer);
    }

    public void howCanIHelp() {
        System.out.println("Would you like to buy, rent, or undo the last transaction?");
        System.out.println("1: rent, 2: buy, 3: undo");
        boolean flag1 = true;
        while (flag1) {
            try {
                choice = input.nextInt();
                if (choice < 1 || choice > 3) {
                    System.out.println("Please try again. 1: rent, 2: buy, 3: undo");
                } else {
                    flag1 = false;
                }
            } catch (InputMismatchException ime) {
                System.out.println("Please enter a valid number");
                input.nextLine(); // ✅ Fix
            }
        }

        switch (choice) {
            case 1:
                paymentType = "rent";
                break;
            case 2:
                paymentType = "buy";
                break;
            case 3:
                // Undo the last transaction
                Transaction.undoTransaction();
                return; // Exit the method after undoing
        }

        // Sorting step (Strategy Pattern)
        CarSorter carSorter = new CarSorter();
        System.out.println("How do you want to sort the cars?");
        System.out.println("1. By ID");
        System.out.println("2. By Price (Low to High)");
        System.out.println("3. By Name (Car Model)");

        int sortChoice = 0;
        boolean sortFlag = true;
        while (sortFlag) {
            try {
                sortChoice = input.nextInt();
                if (sortChoice < 1 || sortChoice > 3) {
                    System.out.println("Please enter 1, 2, or 3:");
                } else {
                    sortFlag = false;
                }
            } catch (InputMismatchException ime) {
                System.out.println("Please enter a valid number");
                input.nextLine();
            }
        }

        switch (sortChoice) {
            case 1:
                carSorter.setStrategy(new SortById());
                break;
            case 2:
                carSorter.setStrategy(new SortByPrice());
                break;
            case 3:
                carSorter.setStrategy(new SortByName());
                break;
        }

        carSorter.sortCars(cars); // Sort cars based on selected strategy

        System.out.println("Choose your car from the available list (ID):");
        System.out.println("Name | ID | Price | Rent");
        for (Car car : cars) {
            if (car != null)
                System.out.println(car.getName() + " | " + car.getId() + " | " + car.getPrice()
                        + " | " + car.getRent());
        }
        System.out.println("_______________________________");
        System.out.println("Please enter the ID of the car you want to " + paymentType + ": ");
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

        // Store transaction before performing any operation
        transaction.setEmployeeAndCustomer(employee, customer);
        switch (paymentType) {
            case "rent":
                transaction.rent(car);
                break;
            case "buy":
                transaction.buy(car);
                break;
            default:
        }

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
