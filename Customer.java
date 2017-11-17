public class Customer implements Comparable{
    private int age,comingtime,endtime,sourcefloor,destinationfloor;
    private String ID;
    private boolean reach = false;

    public Customer(){

    }

    public Customer(String ID,int comingtime,int age,int sourcefloor,int destinationfloor){
        this.ID = ID;
        this.age = age;
        this.age = age;
        this.sourcefloor = sourcefloor;
        this.destinationfloor = destinationfloor;
        this.endtime = comingtime;
    }

    public boolean getReach(){
        return reach;
    }

    public void setReach(){
        reach = true;
    }

    public void setID(String ID){
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    public void setComingtime(int comingtime) {
        this.comingtime = comingtime;
    }

    public int getComingtime() {
        return comingtime;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setSourcefloor(int sourcefloor) {
        this.sourcefloor = sourcefloor;
    }

    public int getSourcefloor() {
        return sourcefloor;
    }

    public void setDestinationfloor(int destinationfloor) {
        this.destinationfloor = destinationfloor;
    }

    public int getDestinationfloor() {
        return destinationfloor;
    }

    public void setEndtime(int endtime) {
        this.endtime = endtime;
    }

    public int getEndtime() {
        return endtime;
    }

    public int compareTo(Object o) {
        if (this.getAge() >= 60 && ((Customer) o).getAge() < 60) {
            return 1;
        }
        return ((Customer) o).getComingtime() - this.getComingtime();
    }
    @Override
    public String toString() {
        return String.format("Customer ID:%s\nComing time:%d\nAge:%d\nSource floor:%d\nDestination floor:%d\nEnding time:%d",
                ID, comingtime, age, sourcefloor, destinationfloor, endtime);
    }
}
