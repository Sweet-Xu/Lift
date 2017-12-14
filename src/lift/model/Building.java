package lift.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 建筑物实体
 * 用来描述一个正常的建筑所具有的组建
 */
public class Building {
    private List<Floor> floors; //描述楼层
    private Lift lift;  //电梯
    private Timer timer;    //计时器

    public Building() {
        floors=new ArrayList<>(100);
        for(int i=0;i<100;i++){
            floors.add(new Floor());
        }
        lift=new Lift();
        timer=new Timer();
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }

    public Lift getLift() {
        return lift;
    }

    public void setLift(Lift lift) {
        this.lift = lift;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
    
    public boolean isAllFloorNullWait(){
        boolean isEmpty=true;
        for (Floor floor :
                floors) {
            isEmpty = isEmpty && floor.isAllEmpty();
        }

        return  isEmpty;
    }
}
