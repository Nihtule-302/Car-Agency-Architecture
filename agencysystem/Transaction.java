package agencysystem;

import identification.Car;
import identification.Customer;
import identification.Employee;

public class Transaction {
    private static double Income = 0;
    private static Transaction[] transactions = new Transaction[5];

    private Employee employee;
    private Customer customer;
    private Car car;
    private String paymentType;
    private double price;
    private static Admin manager = new Admin();

    public void setEmployeeAndCustomer(Employee employee, Customer customer) {
        this.employee = employee;
        this.customer = customer;
    }

    public void buy(Car car){
        this.paymentType = "cash";
        this.car = car;
        employee.increasePayCheck((car.getPrice() * 0.01));
        manager.removeCar(car.getName());
        Income += car.getPrice();
        price = car.getPrice();
    }

    public void rent(Car car){
        this.paymentType = "rent";
        this.car = car;
        employee.increasePayCheck((car.getRent() * 0.01));
        manager.removeCar(car.getName());
        Income += car.getRent();
        price = car.getRent();
    }

    public void saveTransaction(){
        Transaction[] transactions = getTransactions();
        int availableIndex = findAvailableIndex();
        //System.out.println("Employee" + this.employee + "customer " + this.customer + "car " + this.car + "payment type" + paymentType + "Price " + price);
        transactions[availableIndex] = this;
    }

    private int findAvailableIndex(){
        Transaction[] transactions = getTransactions();
        for (int i = 0; i < transactions.length; i++) {
            if(transactions[i] == null)
                return i;
        }
        //if the array is full, resize array
        //and return the last index
        resizeArray();
        return findAvailableIndex();

    }

    private void resizeArray(){

        Transaction[] temp = new Transaction[getTransactions().length + 1];
        System.arraycopy(getTransactions(), 0, temp, 0, getTransactions().length);
        setTransactions(temp);
    }

    public double getIncome() {
        return Income;
    }

    public Transaction[] getTransactions() {
        return transactions;
    }

    public void setTransactions(Transaction[] transactions) {
        Transaction.transactions = transactions;
    }

    public String getPaymentType(){
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
