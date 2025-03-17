package factory;

import identification.*;


public class CustomerFactory {
    public static Customer createCustomer(String name,CustomerType customerType) {
        switch (customerType) {
            case REGULAR:
                return new Regular(name);

            case PREMIUIM:
                return new Premiuim(name);

            case VIP:
                return new Vip(name);

             default:
                throw new IllegalArgumentException("Invalid employee type: " + customerType);
        }
    }
}
