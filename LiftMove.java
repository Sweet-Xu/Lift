public class LiftMove extends Lift {
    private RunningTime liftruntime;
    private int  MaxNumber, passages;
    private int status;//raise = 1,stop = 0,fall= -1;

    public LiftMove(){
        passages = 0;
        status = 0;
    }


    public void setStatus(int status){
        this.status = status;
    }

    public int getStatus(){
        return status;
    }

    public LiftMove(int buttomfloor,int topfloor,int runfloor,int MaxMumber){
        super(buttomfloor,topfloor,runfloor,MaxMumber);
        this.passages = 0;
    }

    @Override
    public void riseFloor() {
        if(getRunfloor() < getTopfloor()) {
            setRunfloor(getRunfloor() + 1);
            liftruntime.setTime(10);
        }
        else
            System.out.println("The top floor");
    }

    @Override
    public void declineFloor() {
        if(getRunfloor() < getTopfloor()) {
            setRunfloor(getRunfloor() - 1);
            liftruntime.setTime(10);
        }
        else
            System.out.println("The buttom floor");
    }

    public void LiftPause(){
        liftruntime.setTime(1);
    }

    public void MumberEnter(){
        if(getMaxMumber() > passages){
            passages++;
            LiftPause();
        }
        else{
            System.out.println("Overload");
        }
    }

    public void MumberOut(){
        if(passages > 0){
            passages--;
            LiftPause();
        }
        else{
            return;
        }
    }
}
