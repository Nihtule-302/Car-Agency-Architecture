package utility;

import EntityTypes.CarType;
import agencysystem.Transaction;
import EntityTypes.EmployeeType;
import agencysystem.Admin;
import factory.CarBuilder;
import identification.Cars.Car;
import identification.Customers.Customer;
import identification.Employees.Employee;
import EntityTypes.CustomerType;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Management {
    private  Admin admin = Admin.getInstance();

    Scanner input = new Scanner(System.in);

    public void start() {
        System.out.println("               (Root)                ");
        System.out.println("Hello Admin\nChoose from the list below the part of the system you want to access");
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
                System.out.println("\n|(1)Employee | (2)Customer | (3)Car | (4)TransactionManagement | (5)Exit |\n");
                System.out.print("Choose the number corresponding to the part of the system to access: ");
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
                        System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
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
            Employee[] employees = admin.getEmployees();
            System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
            System.out.println("              (Employee)            ");
            System.out.println("|(1)Hire | (2)Fire | (3)Search | (4)Show All Employees | (5)return to previous page |");
            System.out.print("Choose from the list above the part of the system to access: ");
            boolean flag = true;
            while (flag) {
                int choice = input.nextInt();
                switch (choice) {
                    case 1:
                        System.out.print("Who do you wish to HIRE (enter a name): ");
                        employeeName = input.next();
                        admin.addEmployee(employeeName,EmployeeType.NORMAL);
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
                            System.out.println("paycheck: " + employee.getPayCheck());
                            flag = false;
                            break;
                        }catch (Exception e){
                            System.out.println("Employee not found");
                            flag = false;
                            break;
                        }
                    case 4:
                        System.out.print("Name    | ");
                        for (int i = 0; i < employees.length; i++) {
                            if (employees[i] != null)
                                System.out.print(employees[i].getName() + " | ");
                        }
                        System.out.print("\nID      | ");
                        for (int i = 0; i < employees.length; i++) {
                            if (employees[i] != null)
                                System.out.print(employees[i].getId() + "   |   ");
                        }
                        System.out.print("\nPaycheck| ");
                        for (int i = 0; i < employees.length; i++) {
                            if (employees[i] != null)
                                System.out.print(employees[i].getPayCheck() + " | ");
                        }
                        flag = false;
                        break;
                    case 5:
                        System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                        System.out.println("               (Root)                ");
                        return;
                    default:
                        System.out.print("please choose the one of the options listed, try again: ");
                }
            }
        }
    }
    private void customerManagement() throws InputMismatchException {
        String customerName;
        Customer customer;
        while (true) {
            Customer[] customers = admin.getCustomers();
            System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
            System.out.println("              (Customer)            ");
            System.out.println("|(1)Add | (2)Search | (3)Show All Customers | (4)return to previous page");
            System.out.print("Choose from the list above the part of the system to access: ");
            boolean flag = true;
            while (flag) {
                int choice = input.nextInt();
                switch (choice) {
                    case 1:
                        System.out.print("Enter the Customer's name: ");
                        customerName = input.next();
                        admin.addCustomer(customerName,CustomerType.REGULAR);
                        System.out.print(customerName + " Added");
                        flag = false;
                        break;
                    case 2:
                        try{
                            System.out.print("Enter the Customer's name: ");
                            customerName = input.next();
                            customer = admin.getCustomer(customerName);
                            System.out.println("Name: " + customer.getName());
                            System.out.println("ID: " + customer.getId());
                            flag = false;
                            break;
                        }catch (Exception e){
                            System.out.println("Customer not found");
                            input.next();
                            flag = false;
                            break;
                        }
                    case 3:
                        System.out.println("Name | ID ");
                        if (customers.length > 0) {
                            for (int i = 0; i < customers.length; i++) {
                                if (customers[i] != null)
                                    System.out.println(customers[i].getName() + "  |  " + customers[i].getId());
                                flag = false;
                                break;
                            }
                        }else 
                            System.out.println("No Customers");
                    case 4:
                        System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                        System.out.println("               (Root)                ");
                        return;
                    default:
                        System.out.print("please choose the one of the options listed, try again: ");
                }
            }
        }
    }
    private void carManagement() throws InputMismatchException {
        String model;
        double price, rent;
        Car car;
        while (true) {
            Car[] cars = admin.getCars();
            System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
            System.out.println("              (Car)            ");
            System.out.println("|(1)Add | (2)Remove | (3)Search | (4)Show All Cars | (5)return to previous page |");
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
                        System.out.print("rent: ");
                        rent = input.nextDouble();

                        car = new CarBuilder()
                                .setModel(model)
                                .setPrice(price)
                                .setRent(rent)
                                .setCarType(CarType.ECONOMY)
                                .build();
                        admin.addCar(car);

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
                            System.out.println("price: " + car.getPrice());
                            System.out.println("rent: " + car.getRent());
                            flag = false;
                            break;
                        }catch (Exception e){
                            System.out.println("Car not found");
                            input.next();
                            flag = false;
                            break;
                        }
                    case 4:
                        System.out.println("Name | ID | Price | rent");
                        for (int i = 0; i < cars.length; i++) {
                            if (cars[i] != null)
                                System.out.println(cars[i].getName() + " | " + cars[i].getId() +
                                                 " | " + cars[i].getPrice() + " | " + cars[i].getRent() + " | ");
                        }
                        flag = false;
                        break;
                    case 5:
                        System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                        System.out.println("               (Root)                ");
                        return;
                    default:
                        System.out.print("please choose the one of the options listed, try again: ");
                }
            }
        }
    }
    private void transactionManagement() throws InputMismatchException {
        while (true) {
            Transaction transaction = new Transaction();
            System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
            System.out.println("            (transactionManagement)            ");
            System.out.println("|(1)Balance | (2)Show All Transactions | (3)return to previous page");
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
                        Transaction[] allTransactions = new Transaction().getTransactions();
                        System.out.println("Employee | Customer | Operation | Car | Price |");
                        for (int i = 0; i < allTransactions.length; i++) {
                            if(allTransactions[i] != null){
                            if (allTransactions[i].getPaymentType().equals("cash") ){
                                System.out.println(allTransactions[i].getEmployeeName() + " | " + allTransactions[i].getCustomerName() +
                                                 " | " + allTransactions[i].getPaymentType() + " | " + allTransactions[i].getCarModel() + " | " + allTransactions[i].getPrice() + " | ");
                            } else if (allTransactions[i].getPaymentType().equals("rent")){
                                System.out.println(allTransactions[i].getEmployeeName() + " | " + allTransactions[i].getCustomerName() +
                                                 " | " + allTransactions[i].getPaymentType() + " | "  + allTransactions[i].getCarModel() + " | " + allTransactions[i].getPrice() + " | ");
                            }
                        }
                    }
                        flag = false;
                        break;
                    case 3:
                        System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                        System.out.println("               (Root)                ");
                        return;
                    default:
                        System.out.print("please choose the one of the options listed, try again: ");
                }
            }
        }
    }
}
