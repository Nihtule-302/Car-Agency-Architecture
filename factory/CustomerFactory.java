package factory;

import EntityTypes.CustomerType;
import identification.Customers.Customer;
import identification.Customers.Premium;
import identification.Customers.Regular;
import identification.Customers.Vip;


public class CustomerFactory {
    public static Customer createCustomer(String name, CustomerType customerType) {
        switch (customerType) {
            case REGULAR:
                return new Regular(name);

            case PREMIUIM:
                return new Premium(name);

            case VIP:
                return new Vip(name);

             default:
                throw new IllegalArgumentException("Invalid employee type: " + customerType);
        }
    }
}
