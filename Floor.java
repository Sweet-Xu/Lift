import java.util.Collections;

import java.util.ArrayList;

public class Floor {
    private ArrayList<Customer> customer;
    private boolean press = false;
    private  int floor = 0;

    public Floor(){
        customer = new ArrayList<Customer>();
    }

    public void FloorSort(){
        Collections.sort(customer);
    }

    public void Press(){
        press = true;
    }

    public boolean getPress(){
        return press;
    }

    public void addCustomer(Customer customer){
        if(floor )
    }
}
