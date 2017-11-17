public class RunningTime {
    private int time;

    public RunningTime(){
        time = 0;
    }

    public void setTime(int time){
        this.time += time;
    }

    public int getTime(){
        return time;
    }
}


