package FactoryandBuilder;

import EntityTypes.EmployeeType;
import identification.Employees.Employee;

public class EmployeeBuilder {
    private String name;
    private EmployeeType employeeType;

    public EmployeeBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public EmployeeBuilder setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
        return this;
    }

    public Employee build() {
        return EmployeeFactory.createEmployee(employeeType, name);
    }
}
