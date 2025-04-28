package FactoryandBuilder;

import EntityTypes.CustomerType;
import identification.Customers.Customer;

public class CustomerBuilder {
    private String name;
    private CustomerType customerType;

    public CustomerBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CustomerBuilder setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
        return this;
    }

    public Customer build() {
        return CustomerFactory.createCustomer(customerType, name);
    }
}
