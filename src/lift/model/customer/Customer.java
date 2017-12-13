package lift.model.customer;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Customer {
    private final IntegerProperty id;
    private final IntegerProperty comingTime;
    private final IntegerProperty age;
    private final IntegerProperty sourceFloor;
    private final IntegerProperty destinationFloor;
    private final IntegerProperty leavingTime;

    public Customer(){
        this(0,0,0,0,0);
    }
    public Customer(int id, int comingTime, int age, int sourceFloor, int destinationFloor){
        this.id=new SimpleIntegerProperty(id);
        this.comingTime=new SimpleIntegerProperty(comingTime);
        this.age=new SimpleIntegerProperty(age);
        this.sourceFloor=new SimpleIntegerProperty(sourceFloor);
        this.destinationFloor=new SimpleIntegerProperty(destinationFloor);
        this.leavingTime=new SimpleIntegerProperty(0);
    }

    public int getId(){
        return this.id.get();
    }

    public void setId(int id){
       this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public int getComingTime(){
        return this.comingTime.get();
    }

    public void setComingTime(int comingTIme){
        this.comingTime.set(comingTIme);
    }

    public IntegerProperty comingTimeProperty() {
        return comingTime;
    }

    public int getAge(){
        return this.age.get();
    }

    public void setAge(int age){
        this.age.set(age);
    }

    public IntegerProperty ageProperty() {
        return age;
    }

    public int getSourceFloor(){
        return this.sourceFloor.get();
    }

    public void setSourceFloor(int sourceFloor){
        this.sourceFloor.set(sourceFloor);
    }

    public IntegerProperty sourceFloorProperty() {
        return sourceFloor;
    }

    public int getDestinationFloor(){
        return this.destinationFloor.get();
    }

    public void setDestinationFloor(int destinationFloor){
        this.destinationFloor.set(destinationFloor);
    }

    public IntegerProperty destinationFloorProperty() {
        return destinationFloor;
    }

    public int getLeavingTime(){
        return this.leavingTime.get();
    }

    public void setLeavingTime(int leavingTime){
        this.leavingTime.set(leavingTime);
    }

    public IntegerProperty leavingTimeProperty() {
        return leavingTime;
    }
}
