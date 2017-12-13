package lift.model;

/**
 * 计时器类
 * 用于存储计时时间
 * 单位： 秒
 * 具有转换功能
 */
public class Timer {
    private long second; //计时

    public Timer() {
        this.second = 0;
    }

    public void addSecond(int i){
        second+=i;
    }

    public long getSecond() {
        return second;
    }

//    public void setSecond(long second) {
//        this.second = second;
//    }
}