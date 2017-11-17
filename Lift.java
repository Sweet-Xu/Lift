public abstract class Lift {
    private int buttomfloor,topfloor,runfloor,MaxMumber;

    public Lift(){}

    public Lift(int buttonfloor,int topfloor,int runfloor,int MaxMumber){
        this.buttomfloor = buttonfloor;
        this.topfloor = topfloor;
        this.runfloor = runfloor;
        this.MaxMumber = MaxMumber;
    }

    public int getButtomfloor() {
        return buttomfloor;
    }

    public int getRunfloor() {
        return runfloor;
    }

    public int getTopfloor() {
        return topfloor;
    }

    public void setButtomfloor(int buttomfloor) {
        this.buttomfloor = buttomfloor;
    }

    public void setRunfloor(int runfloor) {
        this.runfloor = runfloor;
    }

    public void setTopfloor(int topfloor) {
        this.topfloor = topfloor;
    }

    public int getMaxMumber() {
        return MaxMumber;
    }

    public void setMaxMumber(int maxMumber) {
        MaxMumber = maxMumber;
    }

    public abstract void riseFloor();

    public abstract void declineFloor();
}
