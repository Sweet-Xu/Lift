package lift.model.customer;

public class ElderCustomer extends Customer {

    public ElderCustomer() {
    }

    public ElderCustomer(int id, int comingTime, int age, int sourceFloor, int destinationFloor) {
        super(id, comingTime, age, sourceFloor, destinationFloor);
    }
}
