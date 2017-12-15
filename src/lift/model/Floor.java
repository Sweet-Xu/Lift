package lift.model;

import lift.model.customer.Customer;


import java.util.LinkedList;
import java.util.Queue;

public class Floor {
    private Queue<Customer> upElderCustomerQueue;
    private Queue<Customer> upYoungCusomterQueue;

    private Queue<Customer> downElderCustomerQueue;
    private Queue<Customer> downYoungCustomerQueue;

    public Floor() {
        upElderCustomerQueue=new LinkedList<>();
        upYoungCusomterQueue=new LinkedList<>();

        downElderCustomerQueue=new LinkedList<>();
        downYoungCustomerQueue=new LinkedList<>();
    }

    public Queue<Customer> getUpElderCustomerQueue() {
        return upElderCustomerQueue;
    }

    public void setUpElderCustomerQueue(Queue<Customer> upElderCustomerQueue) {
        this.upElderCustomerQueue = upElderCustomerQueue;
    }

    public Queue<Customer> getUpYoungCusomterQueue() {
        return upYoungCusomterQueue;
    }

    public void setUpYoungCusomterQueue(Queue<Customer> upYoungCusomterQueue) {
        this.upYoungCusomterQueue = upYoungCusomterQueue;
    }

    public Queue<Customer> getDownElderCustomerQueue() {
        return downElderCustomerQueue;
    }

    public void setDownElderCustomerQueue(Queue<Customer> downElderCustomerQueue) {
        this.downElderCustomerQueue = downElderCustomerQueue;
    }

    public Queue<Customer> getDownYoungCustomerQueue() {
        return downYoungCustomerQueue;
    }

    public void setDownYoungCustomerQueue(Queue<Customer> downYoungCustomerQueue) {
        this.downYoungCustomerQueue = downYoungCustomerQueue;
    }

    public void inFLoor(Customer customer){
        if(customer.getDestinationFloor()-customer.getSourceFloor()>0){
            if(customer.getAge()>=60){
                upElderCustomerQueue.add(customer);
            }else{
                upYoungCusomterQueue.add(customer);
            }
        }else{
            if(customer.getAge()>=60){
                downElderCustomerQueue.add(customer);
            }else{
                downYoungCustomerQueue.add(customer);
            }
        }

        System.out.println("Id:"+customer.getId()+" in floor");
    }

    public Customer outFloor(int dv){
        return null;
    }

    public boolean isAllEmpty(){
        return isUpEmpty() && isDownEmpty();
    }

    public boolean isUpEmpty(){
        return upElderCustomerQueue.isEmpty() && upYoungCusomterQueue.isEmpty();
    }

    public boolean isDownEmpty(){
        return downElderCustomerQueue.isEmpty() && downYoungCustomerQueue.isEmpty();
    }
}
