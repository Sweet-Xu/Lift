package lift.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;

/**
 * 计时器类
 * 用于存储计时时间
 * 单位： 秒
 * 具有转换功能
 */
public class Timer {
    private IntegerProperty second; //计时

    public Timer() {
        second=new SimpleIntegerProperty(0);
    }

    public void addSecond(){
        addSecond(1);
    }

    public void addSecond(int i){
        second.set(second.get()+1);
    }

    public int getSecond() {
        return second.get();
    }

    public IntegerProperty secondProperty() {
        return second;
    }

    //    public void setSecond(long second) {
//        this.second = second;
//    }

    /**
     * 时钟监听绑定
     * @param label
     */
    public void addClockListener(Label label){
        second.addListener((observable, oldValue, newValue) -> {
            label.setText(newValue.toString());
        });
    }
}