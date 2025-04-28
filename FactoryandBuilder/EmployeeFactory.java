package FactoryandBuilder;

import EntityTypes.EmployeeType;
import identification.Employees.Employee;
import identification.Employees.Hr;
import identification.Employees.Manager;
import identification.Employees.Normal;

public class EmployeeFactory {
    public static Employee createEmployee(EmployeeType employeeType, String name) {
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
