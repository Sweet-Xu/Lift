package lift.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import lift.model.customer.Customer;
import lift.util.CustomerQueue;

public class Lift {
    public static final int LIFT_STOP=0;
    public static final int LIFT_DOOR_OPEN=1;
    public static final int LIFT_DOOR_CLOSE=2;
    public static final int LIFT_UP=3;
    public static final int LIFT_DOWN=4;

    public static final int MAX_BEARING=13;

    private IntegerProperty nowLevel;
    private IntegerProperty door;
    private CustomerQueue<Customer> customers;

    public Lift() {
        nowLevel=new SimpleIntegerProperty(1);
        door=new SimpleIntegerProperty(LIFT_DOOR_CLOSE);
        customers=new CustomerQueue<Customer>();

    }
}