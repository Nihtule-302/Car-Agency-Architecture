package utility;

import agencysystem.Admin;
import agencysystem.Transaction;
import identification.Car;
import identification.Customer;
import identification.Employee;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Talk {
    private Customer customer;
    private Car car;
    private static Admin manager = new Admin();
    private static Car[] cars = manager.getCars();
    private static Transaction transaction = new Transaction();
    Scanner input = new Scanner(System.in);
    private Employee employee;
    private int choice;
    private String paymentType;

    private  Employee getRandomEmployee() {
        Employee[] employees = manager.getEmployees();
        int randomIndex = (int) (Math.random() * employees.length);
        return employees[randomIndex];
    }

    public void greetings(){
        employee = getRandomEmployee();
        System.out.println("Hello, my name is " + employee.getName());
        System.out.println("Please enter your name: ");
        String name = input.next();
        customer = new Customer(name);
        manager.addCustomer(customer);
        transaction.setEmployeeAndCustomer(employee, customer);
    }

    public void howCanIHelp(){
        System.out.println("Would you like to buy or  rent?");
        System.out.println("0: rent, 1: buy");
        boolean flag1 = true;
        while(flag1) {
            try {
                choice = input.nextInt();
                if (choice != 0 && choice != 1) {
                    System.out.println("Please try again. 0: rent, 1: buy");
                } else {
                    flag1 = false;
                }

            } catch (InputMismatchException ime) {
                System.out.println("Please enter a valid number");
                input.nextInt();
            }
        }
        switch(choice){
            case 0: paymentType = "rent";
                break;
            case 1: paymentType = "buy";
                break;
        }

        System.out.println("Choose your car from the available list (ID):");
        System.out.println("Name | ID | Price | rent");
        for (int i = 0; i < cars.length; i++) {
            if (cars[i] != null)
                System.out.println(cars[i].getName() + " | " + cars[i].getId() +
                 " | " + cars[i].getPrice() + " | " + cars[i].getRent() + " | ");
            }

        boolean flag2 = true;
        while(flag2){
            try{
                int carID = input.nextInt();
                car = manager.getCar(carID);
                flag2 = false;        
            }
            catch(Exception e) {
                System.out.println("Please enter a valid ID");
                input.next();
            }   
        }

        switch(paymentType){
            case "rent":
                transaction.rent(car);
                break;
            case "buy":
                transaction.buy(car);
                break;
            default:
            transaction.buy(car);
        }

        transaction.saveTransaction();

        thatWillBe(employee, customer, car, paymentType);
    }
     
    private void thatWillBe(Employee employee, Customer customer, Car car, String paymentType){
        System.out.println("Thank you, " + customer.getName());
        if(paymentType.equals("buy"))
            System.out.println("Your total will be: " + car.getPrice());
        if(paymentType.equals("rent"))
            System.out.println("Your total will be: " + car.getRent());
    }

}
