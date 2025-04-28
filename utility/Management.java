package utility;

import EntityTypes.CarType;
import agencysystem.Transaction;
import EntityTypes.EmployeeType;
import FactoryandBuilder.CarBuilder;
import FactoryandBuilder.CustomerBuilder;
import FactoryandBuilder.EmployeeBuilder;
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
                        hireEmployee();
                        flag = false;
                        break;
                    case 2:
                        fireEmployee();
                        flag = false;
                        break;
                    case 3:
                        searchEmployee();
                        flag = false;
                        break;
                    case 4:
                        showAllEmployees(employees);
                        flag = false;
                        break;
                    case 5:
                        returnToPrevious();
                        return;
                    default:
                        System.out.print("Please choose one of the options listed, try again: ");
                }
            }
        }
    }

    private void hireEmployee() {
        System.out.print("Who do you wish to HIRE (enter a name): ");
        String employeeName = input.next();
        System.out.println("Choose Employee Type: (1) Normal | (2) Manager");
        int employeeTypeChoice = input.nextInt();
        EmployeeType employeeType =
                employeeTypeChoice == 1 ? EmployeeType.NORMAL : EmployeeType.MANAGER;
        Employee employee =
                new EmployeeBuilder().setName(employeeName).setEmployeeType(employeeType).build();
        admin.addEmployee(employee);
        System.out.println(employeeName + " is hired");
    }

    private void fireEmployee() {
        System.out.print("Who do you wish to FIRE!!! (enter a name): ");
        String employeeName = input.next();
        admin.removeEmployee(employeeName);
        System.out.println(employeeName + " is fired");
    }

    private void searchEmployee() {
        System.out.print("Who do you wish to Find (enter a name): ");
        String employeeName = input.next();
        try {
            Employee employee = admin.getEmployee(employeeName);
            System.out.println("Name: " + employee.getName());
            System.out.println("ID: " + employee.getId());
            System.out.println("Paycheck: " + employee.getPayCheck());
        } catch (Exception e) {
            System.out.println("Employee not found");
        }
    }

    private void showAllEmployees(List<Employee> employees) {
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
    }

    private void returnToPrevious() {
        System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        System.out.println("               (Root)                ");
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
                        addCustomer();
                        flag = false;
                        break;
                    case 2:
                        searchCustomer();
                        flag = false;
                        break;
                    case 3:
                        showAllCustomers(customers);
                        flag = false;
                        break;
                    case 4:
                        returnToPrevious();
                        return;
                    default:
                        System.out.print("Please choose one of the options listed, try again: ");
                }
            }
        }
    }

    private void addCustomer() {
        System.out.print("Enter the Customer's name: ");
        String customerName = input.next();
        System.out.println("Choose Customer Type: (1) Regular | (2) VIP");
        int customerTypeChoice = input.nextInt();
        CustomerType customerType =
                customerTypeChoice == 1 ? CustomerType.REGULAR : CustomerType.VIP;
        Customer customer =
                new CustomerBuilder().setName(customerName).setCustomerType(customerType).build();
        admin.addCustomer(customer);
        System.out.println(customerName + " Added");
    }

    private void searchCustomer() {
        System.out.print("Enter the Customer's name: ");
        String customerName = input.next();
        try {
            Customer customer = admin.getCustomer(customerName);
            System.out.println("Name: " + customer.getName());
            System.out.println("ID: " + customer.getId());
        } catch (Exception e) {
            System.out.println("Customer not found");
        }
    }

    private void showAllCustomers(List<Customer> customers) {
        System.out.println("Name | ID");
        for (Customer cust : customers) {
            System.out.println(cust.getName() + "  |  " + cust.getId());
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
                        addCar();
                        flag = false;
                        break;
                    case 2:
                        removeCar();
                        flag = false;
                        break;
                    case 3:
                        searchCar();
                        flag = false;
                        break;
                    case 4:
                        showAllCars(cars);
                        flag = false;
                        break;
                    case 5:
                        returnToPrevious();
                        return;
                    default:
                        System.out.print("Please choose one of the options listed, try again: ");
                }
            }
        }
    }

    private void addCar() {
        System.out.print("Enter the model of the car: ");
        String model = input.next();
        System.out.print("Price: ");
        double price = input.nextDouble();
        System.out.print("Rent: ");
        double rent = input.nextDouble();

        System.out.println("Choose Car Type: (1) Economy | (2) Luxury | (3) Sports");
        int carTypeChoice = input.nextInt();
        CarType carType = switch (carTypeChoice) {
            case 1 -> CarType.ECONOMY;
            case 2 -> CarType.LUXURY;
            case 3 -> CarType.SPORTS;
            default -> CarType.ECONOMY;
        };
        Car car = new CarBuilder().setModel(model).setPrice(price).setRent(rent).setCarType(carType)
                .build();
        admin.addCar(car);
        System.out.println(model + " Added");
    }

    private void removeCar() {
        System.out.print("Which car do you want to remove: ");
        String model = input.next();
        admin.removeCar(model);
        System.out.println(model + " Removed");
    }

    private void searchCar() {
        System.out.print("Enter the Car's model: ");
        String model = input.next();
        try {
            Car car = admin.getCar(model);
            System.out.println("Name: " + car.getName());
            System.out.println("ID: " + car.getId());
            System.out.println("Price: " + car.getPrice());
            System.out.println("Rent: " + car.getRent());
        } catch (Exception e) {
            System.out.println("Car not found");
        }
    }

    private void showAllCars(List<Car> cars) {
        System.out.println("Name | ID | Price | Rent");
        for (Car c : cars) {
            System.out.println(
                    c.getName() + " | " + c.getId() + " | " + c.getPrice() + " | " + c.getRent());
        }
    }

    private void transactionManagement() throws InputMismatchException {
        while (true) {
            System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
            System.out.println("            (transactionManagement)            ");
            System.out
                    .println("|(1)Balance | (2)Show All Transactions | (3)return to previous page");
            System.out
                    .print("Choose the number corresponding to the part of the system to access: ");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    showBalance();
                    break;
                case 2:
                    showTransactions();
                    break;
                case 3:
                    returnToPrevious();
                    return;
                default:
                    System.out.print("please choose one of the options listed, try again: ");
            }
        }
    }

    private void showBalance() {
        // Show total income from all transactions
        System.out.println("Total Income: " + Transaction.Income);
    }

    private void showTransactions() {
        // Show all transactions in the transaction list
        if (Transaction.transactions.isEmpty()) {
            System.out.println("No transactions available.");
        } else {
            System.out.println("All Transactions:");
            for (Transaction t : Transaction.transactions) {
                System.out.println("Employee: " + t.getEmployeeName() + " | Customer: "
                        + t.getCustomerName() + " | Car: " + t.getCarModel() + " | Payment Type: "
                        + t.getPaymentType() + " | Price: " + t.getPrice());
            }
        }
    }

}
