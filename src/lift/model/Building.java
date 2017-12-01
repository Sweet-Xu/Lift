package lift.model;

import java.util.List;

/**
 * 建筑物实体
 * 用来描述一个正常的建筑所具有的组建
 */
public class Building {
    private List<Floor> floors; //描述楼层
    private Lift lift;  //电梯
    private Timer timer;    //计时器
}
