package identification.Employees;

import identification.Identification;

public class Employee extends Identification {
    private double payCheck = 10000;

    public Employee() {}

    public Employee(String name) {
        setName(name);
    }

    public void increasePayCheck(double money) {
        this.payCheck += money;
    }

    public void decreasePayCheck(double money) {
        this.payCheck -= money;
    }

    public double getPayCheck() {
        return payCheck;
    }
}
