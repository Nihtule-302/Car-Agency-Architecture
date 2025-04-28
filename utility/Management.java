package utility;

import EntityTypes.CarType;
import agencysystem.Transaction;
import EntityTypes.EmployeeType;
import agencysystem.Admin;
import identification.Cars.Car;
import identification.Customers.Customer;
import identification.Employees.Employee;
import EntityTypes.CustomerType;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Management {
    private Admin admin = Admin.getInstance();
    private Scanner input = new Scanner(System.in);

    public void start() {
        System.out.println("               (Root)                ");
        System.out.println(
                "Hello Admin\nChoose from the list below the part of the system you want to access");
        while (true) {
            try {
                chooseEmployeeCustomerCarOrTransactionManagement();
                return;
            } catch (InputMismatchException ime) {
                System.out.print("Enter an integer, try again\n");
                input.next();
            }
        }
    }

    private void chooseEmployeeCustomerCarOrTransactionManagement() throws InputMismatchException {
        while (true) {
            try {
                System.out.println(
                        "\n|(1)Employee | (2)Customer | (3)Car | (4)TransactionManagement | (5)Exit |\n");
                System.out.print(
                        "Choose the number corresponding to the part of the system to access: ");
                int choice = input.nextInt();
                switch (choice) {
                    case 1:
                        employeeManagement();
                        break;
                    case 2:
                        customerManagement();
                        break;
                    case 3:
                        carManagement();
                        break;
                    case 4:
                        transactionManagement();
                        break;
                    case 5:
                        System.out.println(
                                "\n++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                        return;
                    default:
                        System.out.print("please choose one of the options listed, try again");
                }
            } catch (InputMismatchException ime) {
                System.out.print("Enter an integer, try again");
                input.nextLine();
            }
        }
    }

    private void employeeManagement() throws InputMismatchException {
        String employeeName;
        Employee employee;
        while (true) {
            List<Employee> employees = admin.getEmployees();
            System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
            System.out.println("              (Employee)            ");
            System.out.println(
                    "|(1)Hire | (2)Fire | (3)Search | (4)Show All Employees | (5)return to previous page |");
            System.out.print("Choose from the list above the part of the system to access: ");
            boolean flag = true;
            while (flag) {
                int choice = input.nextInt();
                switch (choice) {
                    case 1:
                        System.out.print("Who do you wish to HIRE (enter a name): ");
                        employeeName = input.next();
                        System.out.println("Choose Employee Type: (1) Normal | (2) Manager");
                        int employeeTypeChoice = input.nextInt();
                        EmployeeType employeeType = EmployeeType.NORMAL; // Default value
                        switch (employeeTypeChoice) {
                            case 1:
                                employeeType = EmployeeType.NORMAL;
                                break;
                            case 2:
                                employeeType = EmployeeType.MANAGER;
                                break;
                            default:
                                System.out.println("Invalid choice, setting default to Normal.");
                        }
                        admin.addEmployee(employeeName, employeeType);
                        System.out.print(employeeName + " is hired");
                        flag = false;
                        break;
                    case 2:
                        System.out.print("Who do you wish to FIRE!!! (enter a name): ");
                        employeeName = input.next();
                        admin.removeEmployee(employeeName);
                        System.out.print(employeeName + " is fired");
                        flag = false;
                        break;
                    case 3:
                        try {
                            System.out.print("Who do you wish to Find (enter a name): ");
                            employeeName = input.next();
                            employee = admin.getEmployee(employeeName);
                            System.out.println("Name: " + employee.getName());
                            System.out.println("ID: " + employee.getId());
                            System.out.println("Paycheck: " + employee.getPayCheck());
                            flag = false;
                            break;
                        } catch (Exception e) {
                            System.out.println("Employee not found");
                            flag = false;
                            break;
                        }
                    case 4:
                        System.out.print("Name    | ");
                        for (Employee emp : employees) {
                            System.out.print(emp.getName() + " | ");
                        }
                        System.out.print("\nID      | ");
                        for (Employee emp : employees) {
                            System.out.print(emp.getId() + "   |   ");
                        }
                        System.out.print("\nPaycheck| ");
                        for (Employee emp : employees) {
                            System.out.print(emp.getPayCheck() + " | ");
                        }
                        flag = false;
                        break;
                    case 5:
                        System.out.println(
                                "\n++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                        System.out.println("               (Root)                ");
                        return;
                    default:
                        System.out.print("Please choose one of the options listed, try again: ");
                }
            }
        }
    }


    private void customerManagement() throws InputMismatchException {
        String customerName;
        Customer customer;
        while (true) {
            List<Customer> customers = admin.getCustomers();
            System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
            System.out.println("              (Customer)            ");
            System.out.println(
                    "|(1)Add | (2)Search | (3)Show All Customers | (4)return to previous page");
            System.out.print("Choose from the list above the part of the system to access: ");
            boolean flag = true;
            while (flag) {
                int choice = input.nextInt();
                switch (choice) {
                    case 1:
                        System.out.print("Enter the Customer's name: ");
                        customerName = input.next();
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
                        admin.addCustomer(customerName, customerType);
                        System.out.print(customerName + " Added");
                        flag = false;
                        break;
                    case 2:
                        try {
                            System.out.print("Enter the Customer's name: ");
                            customerName = input.next();
                            customer = admin.getCustomer(customerName);
                            System.out.println("Name: " + customer.getName());
                            System.out.println("ID: " + customer.getId());
                            flag = false;
                            break;
                        } catch (Exception e) {
                            System.out.println("Customer not found");
                            input.next();
                            flag = false;
                            break;
                        }
                    case 3:
                        System.out.println("Name | ID");
                        for (Customer cust : customers) {
                            System.out.println(cust.getName() + "  |  " + cust.getId());
                        }
                        flag = false;
                        break;
                    case 4:
                        System.out.println(
                                "\n++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                        System.out.println("               (Root)                ");
                        return;
                    default:
                        System.out.print("Please choose one of the options listed, try again: ");
                }
            }
        }
    }


    private void carManagement() throws InputMismatchException {
        String model;
        double price, rent;
        Car car;
        while (true) {
            List<Car> cars = admin.getCars();
            System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
            System.out.println("              (Car)            ");
            System.out.println(
                    "|(1)Add | (2)Remove | (3)Search | (4)Show All Cars | (5)return to previous page |");
            System.out.print("Choose from the list above the part of the system to access: ");
            boolean flag = true;
            while (flag) {
                int choice = input.nextInt();
                switch (choice) {
                    case 1:
                        System.out.print("Enter the model of the car: ");
                        model = input.next();
                        System.out.print("Price: ");
                        price = input.nextDouble();
                        System.out.print("Rent: ");
                        rent = input.nextDouble();

                        // Ask the user for the car type
                        System.out
                                .println("Choose Car Type: (1) Economy | (2) Luxury | (3) Sports");
                        int carTypeChoice = input.nextInt();
                        CarType carType = CarType.ECONOMY; // Default value
                        switch (carTypeChoice) {
                            case 1:
                                carType = CarType.ECONOMY;
                                break;
                            case 2:
                                carType = CarType.LUXURY;
                                break;
                            case 3:
                                carType = CarType.SPORTS;
                                break;
                            default:
                                System.out.println("Invalid choice, setting default to Economy.");
                        }

                        // Create car using the factory
                        admin.addCar(model, price, rent, carType); // Adds the car to the system

                        System.out.print(model + " Added");
                        flag = false;
                        break;
                    case 2:
                        System.out.print("Which car do you want to remove: ");
                        model = input.next();
                        admin.removeCar(model);
                        System.out.print(model + " Removed");
                        flag = false;
                        break;
                    case 3:
                        try {
                            System.out.print("Enter the Car's model: ");
                            model = input.next();
                            car = admin.getCar(model);
                            System.out.println("Name: " + car.getName());
                            System.out.println("ID: " + car.getId());
                            System.out.println("Price: " + car.getPrice());
                            System.out.println("Rent: " + car.getRent());
                            flag = false;
                            break;
                        } catch (Exception e) {
                            System.out.println("Car not found");
                            input.next();
                            flag = false;
                            break;
                        }
                    case 4:
                        System.out.println("Name | ID | Price | Rent");
                        for (Car c : cars) {
                            System.out.println(c.getName() + " | " + c.getId() + " | "
                                    + c.getPrice() + " | " + c.getRent());
                        }
                        flag = false;
                        break;
                    case 5:
                        System.out.println(
                                "\n++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                        System.out.println("               (Root)                ");
                        return;
                    default:
                        System.out.print("Please choose one of the options listed, try again: ");
                }
            }
        }
    }



    private void transactionManagement() throws InputMismatchException {
        while (true) {
            Transaction transaction = new Transaction();
            System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
            System.out.println("            (transactionManagement)            ");
            System.out
                    .println("|(1)Balance | (2)Show All Transactions | (3)return to previous page");
            System.out.print("Choose from the list above the part of the system to access: ");
            boolean flag = true;
            while (flag) {
                int choice = input.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Income: " + transaction.getIncome());
                        flag = false;
                        break;
                    case 2:
                        List<Transaction> allTransactions = transaction.getTransactions();
                        System.out.println("Employee | Customer | Operation | Car | Price |");
                        for (Transaction t : allTransactions) {
                            if (t != null) {
                                System.out.println(t.getEmployeeName() + " | " + t.getCustomerName()
                                        + " | " + t.getPaymentType() + " | " + t.getCarModel()
                                        + " | " + t.getPrice() + " | ");
                            }
                        }
                        flag = false;
                        break;
                    case 3:
                        System.out.println(
                                "\n++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                        System.out.println("               (Root)                ");
                        return;
                    default:
                        System.out
                                .print("please choose the one of the options listed, try again: ");
                }
            }
        }
    }
}
