package lift.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import lift.model.customer.Customer;
import lift.util.CustomerQueue;

import java.util.Comparator;
import java.util.Queue;

public class Lift {
    public static final int LIFT_STOP=0;
    public static final int LIFT_DOOR_OPEN=1;
    public static final int LIFT_DOOR_CLOSE=2;
    public static final int LIFT_UP=3;
    public static final int LIFT_DOWN=4;
    public static final int LIFT_RUNNING=5;
    public static final int LIFT_LOADING_CUSTOMER=6;
    public static final int LIFT_DISCHARGE_CUSTOMER=7;

    public static final int MAX_BEARING=13;

    private IntegerProperty nowLevel;
    private IntegerProperty door;
    private IntegerProperty state;
    private int stateLD;
    private IntegerProperty direction;
    private CustomerQueue<Customer> customers;
    private IntegerProperty customerNumber;

    private Comparator<Customer> upComparator;
    private Comparator<Customer> downComparator;


    public Lift() {
        upComparator= Comparator.comparingInt(Customer::getDestinationFloor);
        downComparator=(o1,o2)-> o2.getDestinationFloor()-o1.getDestinationFloor();

        nowLevel=new SimpleIntegerProperty(1);
        door=new SimpleIntegerProperty(LIFT_DOOR_CLOSE);
        customerNumber=new SimpleIntegerProperty(0);
        state=new SimpleIntegerProperty(LIFT_STOP);
        direction=new SimpleIntegerProperty(LIFT_UP);
        stateLD=LIFT_DISCHARGE_CUSTOMER;
        turnUp();
    }

    public int getNowLevel() {
        return nowLevel.get();
    }

    public IntegerProperty nowLevelProperty() {
        return nowLevel;
    }

    public void setNowLevel(int nowLevel) {
        this.nowLevel.set(nowLevel);
    }

    public int getDoor() {
        return door.get();
    }

    public IntegerProperty doorProperty() {
        return door;
    }

    public void setDoor(int door) {
        this.door.set(door);
    }

    public int getState() {
        return state.get();
    }

    public IntegerProperty stateProperty() {
        return state;
    }

    public void setState(int state) {
        this.state.set(state);
    }

    public int getDirection() {
        return direction.get();
    }

    public IntegerProperty directionProperty() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction.set(direction);
    }

    public CustomerQueue<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(CustomerQueue<Customer> customers) {
        this.customers = customers;
    }

    public int getCustomerNumber() {
        return customerNumber.get();
    }

    public IntegerProperty customerNumberProperty() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber.set(customerNumber);
    }

    public void openDoor(){
        if (door.get()!=LIFT_DOOR_OPEN)
            setDoor(LIFT_DOOR_OPEN);
    }

    public int getStateLD() {
        return stateLD;
    }

    public void setStateLD(int stateLD) {
        this.stateLD = stateLD;
    }

    public void closeDoor(){
        if (door.get()!=LIFT_DOOR_CLOSE)
            setDoor(LIFT_DOOR_CLOSE);
    }

    /**
     * 乘客进入电梯方法
     * @param floor
     * @return
     */
    public boolean inLift(Floor floor){
        if(customerNumber.get()<=MAX_BEARING){
            Queue<Customer> queue=switchQueue(floor);
            if (!queue.isEmpty()) {
                openDoor();
                Customer customer = queue.poll();
                customers.add(customer);
                System.out.println("顾客Id： " + customer.getId() + " 进入电梯");
                customerNumber.set(customers.size());
                return true;
            }else{

                return false;
            }
        }else{
            System.out.println("电梯满载！！");
            return false;
        }
    }

    /**
     * 乘客出电梯
     * @return customer
     */
    public Customer outLift(){
        Customer customer=null;
        if (customerNumber.get()>0&&!customers.isEmpty()){
            if (customers.peek().getDestinationFloor()==getNowLevel()) {
                openDoor();
                customer = customers.poll();
                customerNumber.set(customers.size());
            }
        }

        return customer;
    }

    /**
     * 更改向上运行
     */
    public void turnUp(){
        if(getDirection()!=LIFT_UP || customers==null){
            customers = new CustomerQueue<>(upComparator);
            customerNumber.set(customers.size());
            System.out.println("更改 向上");
            setDirection(LIFT_UP);
        }
    }

    /**
     * 更改向下运行
     */
    public void turnDown(){
        if(getDirection()!=LIFT_DOWN || customers==null) {
            customers = new CustomerQueue<>(downComparator);
            customerNumber.set(customers.size());
            System.out.println("更改 向下");
            setDirection(LIFT_DOWN);
        }
    }

    /**
     * 电梯运行
     */
    public void run(){
        setState(LIFT_RUNNING);
    }

    /**
     * 电梯停止
     */
    public void stop(){
        setState(LIFT_STOP);
    }

    /**
     *
     * @param labels
     */
    public void addNowLevelChangeListener(Label... labels){
        nowLevel.addListener((observable, oldValue, newValue) -> {
            for (Label label :
                    labels) {
                label.setText(newValue.toString());
            }
        });
    }

    /**
     *
     * @param rectangles
     */
    public void addDoorChangeListener(Rectangle ... rectangles){

        door.addListener((observable, oldValue, newValue) -> {
            for (Rectangle rectangle :
                    rectangles) {
                if(newValue.intValue()==LIFT_DOOR_CLOSE){
                    rectangle.setWidth(110);
                }else if (newValue.intValue()==LIFT_DOOR_OPEN){
                    rectangle.setWidth(0);
                }
            }
        });
    }


    /**
     *
     * @param label
     */
    public void addCustomerNumberListener(Label label){
        customerNumber.addListener((observable, oldValue, newValue) -> {
            label.setText(newValue.toString());
        });
    }

    /**
     *
     * @param label
     * @param upPolygon
     * @param downPolygon
     */
    public void addLiftStateChangeListener(Label label, Polygon upPolygon, Polygon downPolygon){
        state.addListener((observable, oldValue, newValue) -> {
            Color fouceColor=Color.color(1,0.6901,0);
            switch (newValue.intValue()){
                case LIFT_STOP:
                    label.setText("停止");
                    upPolygon.setFill(Color.BLACK);
                    downPolygon.setFill(Color.BLACK);
                    break;
                case LIFT_RUNNING:
                    String msg="";
                    if (direction.get()==LIFT_UP){
                        msg="向上 ";
                        upPolygon.setFill(fouceColor);
                        downPolygon.setFill(Color.BLACK);
                    }else if(direction.get()==LIFT_DOWN){
                        msg="向下 ";
                        upPolygon.setFill(Color.BLACK);
                        downPolygon.setFill(fouceColor);
                    }
                    msg+="运行";
                    label.setText(msg);
            }
        });
    }



    private Queue<Customer> switchQueue(Floor floor){
        Queue<Customer> queue=null;
        switch (getDirection()){
            case LIFT_UP:
                if (floor.getUpElderCustomerQueue().isEmpty()){
                    queue=floor.getUpYoungCusomterQueue();
                }else{
                    queue=floor.getUpElderCustomerQueue();
                }
                break;
            case LIFT_DOWN:
                if (floor.getDownElderCustomerQueue().isEmpty()){
                    queue=floor.getDownYoungCustomerQueue();
                }else{
                    queue=floor.getDownElderCustomerQueue();
                }
                break;
        }
        return queue;
    }


}