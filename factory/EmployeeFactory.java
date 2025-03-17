package factory;

import identification.Employee;
import identification.Manager;
import identification.Hr;
import identification.Normal;

public class EmployeeFactory {
    public static Employee createEmployee(String name,EmployeeType employeeType) {
        switch (employeeType) {
            case MANAGER:
                return new Manager(name);

            case NORMAL:
                return new Normal(name);

            case HR:
                return new Hr(name);

             default:
                throw new IllegalArgumentException("Invalid employee type: " + employeeType);
        }
    }
}
