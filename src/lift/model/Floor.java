package lift.model;

import lift.model.customer.ElderCustomer;
import lift.model.customer.YoungCustomer;
import lift.util.CustomerQueue;

public class Floor {
    private CustomerQueue<ElderCustomer> upElderCustomerQueue;
    private CustomerQueue<YoungCustomer> upYoungCusomterQueue;

    private CustomerQueue<ElderCustomer> downElderCustomerQueue;
    private CustomerQueue<YoungCustomer> downYoungCustomerQueue;

    public Floor() {
        upElderCustomerQueue=new CustomerQueue<>();
        upYoungCusomterQueue=new CustomerQueue<>();

        downElderCustomerQueue=new CustomerQueue<>();
        downYoungCustomerQueue=new CustomerQueue<>();
    }
}
