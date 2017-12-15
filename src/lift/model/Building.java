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

    /**
     * 判断此楼以上是否有等待上行的人
     * @param index
     * @return
     */
    public boolean isUpNullWaitFromThisFloor(int index){
        boolean isEmpty=true;
        for (int i=index-1;i<100;i++){
            isEmpty=isEmpty && floors.get(i).isUpEmpty();
        }
        for (int i=index;i<100;i++){
            isEmpty=isEmpty && floors.get(i).isDownEmpty();
        }
        return isEmpty;
    }

    /**
     * 判断此楼一下是否有等待下行的人
     * @param index
     * @return
     */
    public boolean isDownNullWaitFromThisFloor(int index){
        boolean isEmpty=true;
        for(int i=index-1;i>0;i--){
            isEmpty=isEmpty && floors.get(i).isDownEmpty();
        }
        for(int i=index-2;i>0;i--){
            isEmpty=isEmpty && floors.get(i).isUpEmpty();
        }
        return isEmpty;
    }
}
