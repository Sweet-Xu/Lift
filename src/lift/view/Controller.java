package lift.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import lift.Main;
import lift.model.Building;
import lift.model.Lift;
import lift.model.customer.Customer;
import lift.model.customer.ElderCustomer;
import lift.model.customer.YoungCustomer;
import lift.util.AlertUtil;
import lift.util.CustomerQueue;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    private Main mainApp;

    private Timeline timeLine;
    private KeyFrame liftMiu;

    private Building building;
    private CustomerQueue<Customer> customersQueue;

    @FXML
    private TableView<Customer> customersTableView;
    @FXML
    private TableColumn<Customer,Number> customerIdColumn;
    @FXML
    private TableColumn<Customer,Number> customerComingTimeColumn;
    @FXML
    private TableColumn<Customer,Number> customerAgeColumn;
    @FXML
    private TableColumn<Customer,Number> customerResFloorColumn;
    @FXML
    private TableColumn<Customer,Number> customerDesFloorColumn;
    @FXML
    private TableColumn<Customer,Number> customerLeaveFloorColumn;

    @FXML
    private TextField comingTimeTextField;
    @FXML
    private TextField ageTextField;
    @FXML
    private TextField resFloorTextField;
    @FXML
    private TextField desFloorTextField;

    @FXML
    private Button playButton;

    @FXML
    private Label liftLevelLCLabel;
    @FXML
    private Label liftLevelLabel;
    @FXML
    private Label stateLabel;
    @FXML
    private Label customerNumberLabel;

    @FXML
    private Label timeLockLabel;

    @FXML
    private Rectangle leftDoorRectangle;
    @FXML
    private Rectangle rightDoorRectangle;
    @FXML
    private Polygon upPolygon;
    @FXML
    private Polygon downPolygon;

    private int liftClock=0;//成员变量 用于记录某个状态的时间钟

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        building=new Building();
        bindTableColumns();
        addBuildingListener();
        setAnimation();
        initCustomerQueue();
    }

    private void initCustomerQueue(){
        customersQueue=new CustomerQueue<>((o1, o2) -> (o1.getComingTime()-o2.getComingTime()));
    }

    /**
     * Table绑定数据资源
     */
    private void bindTableColumns(){
        customerIdColumn.setCellValueFactory(callData->callData.getValue().idProperty());
        customerComingTimeColumn.setCellValueFactory(callData->callData.getValue().comingTimeProperty());
        customerAgeColumn.setCellValueFactory(callData->callData.getValue().ageProperty());
        customerResFloorColumn.setCellValueFactory(callData->callData.getValue().sourceFloorProperty());
        customerDesFloorColumn.setCellValueFactory(callData->callData.getValue().destinationFloorProperty());
        customerLeaveFloorColumn.setCellValueFactory(callData->callData.getValue().leavingTimeProperty());
    }

    /**
     * 添加重要的监听
     */
    private void addListener(){
        //添加集合被更改的监听
        mainApp.getCustomersData().addListener((ListChangeListener<Customer>) c -> {
            //如果顾客列表更新了，将激活PlayButton
            if (mainApp.getCustomersData().size()>0){
                playButton.setDisable(false);
            }else{
                playButton.setDisable(true);
            }
        });
    }

    /**
     * 绑定建筑物相关的监听
     * 用来对界面信息自动更新的
     */
    private void addBuildingListener(){
        building.getTimer().addClockListener(timeLockLabel);
        building.getLift().addNowLevelChangeListener(liftLevelLabel,liftLevelLCLabel);
        building.getLift().addDoorChangeListener(leftDoorRectangle,rightDoorRectangle);
        building.getLift().addCustomerNumberListener(customerNumberLabel);
        building.getLift().addLiftStateChangeListener(stateLabel,upPolygon,downPolygon);
    }

    /**
     * 添加顾客事件
     * 使用FXML标记绑定->Button
     */
    @FXML
    public void handleAddCustomer(){
        if(assetTextField()){
            insertNewCustomer();
        }
    }

    /**
     * 删除顾客事件
     * 使用FXML标记绑定->Button
     */
    @FXML
    public void handleDeleteCustomer(){
        int selectedIndex=customersTableView.getSelectionModel().getSelectedIndex();
        if(selectedIndex>=0){
            Customer customer=customersTableView.getItems().remove(selectedIndex);
            customersQueue.remove(customer);
            System.out.println("customers="+mainApp.getCustomersData().size());
        }
    }

    /**
     * 启动模拟事件
     */
    @FXML
    public void handleStartSimulate(){
        timeLine.play();
    }

    /**
     * 暂停模拟事件
     */
    @FXML
    public void handlePauseSimulate(){
        timeLine.pause();
    }

    /**
     * 设置主类对象
     * @param mainApp
     */
    public void setMainApp(Main mainApp){
        this.mainApp=mainApp;

        customersTableView.setItems(mainApp.getCustomersData());
        addListener();
    }

    /**
     * 判断输入是否无误
     * @return boolean
     */
    private boolean assetTextField(){
        return (assetTextFieldIsEmpty() && assetTextFieldContent());
    }

    /**
     * 判断是否有未填写
     * 没有空缺返回true
     * 反之 false
     * @return boolean
     */
    private boolean assetTextFieldIsEmpty(){
        String mesg="";
        boolean isAsset=true;
        if (comingTimeTextField.getText().isEmpty()){
            mesg+="到达时间不能为空\n";
            isAsset=false;
        }
        if (ageTextField.getText().isEmpty()){
            mesg+="年龄不能为空\n";
            isAsset=false;
        }
        if (resFloorTextField.getText().isEmpty()){
            mesg+="所在楼层不能为空\n";
            isAsset=false;
        }
        if(desFloorTextField.getText().isEmpty()){
            mesg+="到达楼层不能为空\n";
            isAsset=false;
        }

        if (!isAsset){
            AlertUtil errorMeg=new AlertUtil(Alert.AlertType.ERROR,mesg);
            errorMeg.showAlert();
        }

        return isAsset;
    }

    /**
     * 判断输入的内容是否无误
     * @return boolean
     */
    private boolean assetTextFieldContent(){
        String mesg="";
        boolean isAsset=true;

        if (!(Integer.parseInt(comingTimeTextField.getText())>0)){
            mesg+="到达时间必须大于0\n";
            isAsset=false;
        }
        if (!(Integer.parseInt(ageTextField.getText())>0)){
            mesg+="年龄必须大于0\n";
            isAsset=false;
        }
        {
            int floor=Integer.parseInt(resFloorTextField.getText());
            if(!(floor>=1 && floor<=100)){
                mesg+="所在楼层范围：1-100\n";
                isAsset=false;
            }

            int desFloor=Integer.parseInt(desFloorTextField.getText());
            if(!(desFloor>=1 && desFloor<=100)){
                mesg+="到达楼层范围：1-100\n";
                isAsset=false;
            }else {
                if (desFloor==floor){
                    mesg+="到达楼层与所在楼层不能相同\n";
                }
            }
        }

        if (!isAsset){
            AlertUtil errorAlert=new AlertUtil(Alert.AlertType.ERROR,mesg);
            errorAlert.showAlert();
        }

        return isAsset;
    }

    /**
     * 创建新的顾客对象
     * 根据年龄实现相对应的类
     * age>60 ->elderCustomer
     * else ->youngerCustomer
     *
     * 将数据传入列表数组中
     */
    private void insertNewCustomer(){
        int id;
        if (mainApp.getCustomersData().size()==0){
            id=1;
        }else{
            id=mainApp.getCustomersData().get(mainApp.getCustomersData().size()-1).getId()+1;
        }
        int comingTime=Integer.parseInt(comingTimeTextField.getText());
        int age=Integer.parseInt(ageTextField.getText());
        int resFloor=Integer.parseInt(resFloorTextField.getText());
        int desFloor=Integer.parseInt(desFloorTextField.getText());

        Customer customer;
        if(age>=60){
            customer=new ElderCustomer(id,comingTime,age,resFloor,desFloor);
        }else{
            customer=new YoungCustomer(id,comingTime,age,resFloor,desFloor);
        }

        mainApp.getCustomersData().add(customer);
        customersQueue.add(customer);
    }

    /**
     * 设置动画
     * 设置动画为无限循环
     */
    private void setAnimation(){
        setKeyFrame();
        timeLine=new Timeline(liftMiu);
        timeLine.setCycleCount(Timeline.INDEFINITE);
    }

    /**
     * 设置动画关键帧
     */
    private void setKeyFrame(){
        EventHandler<ActionEvent> eventHandler=event -> {
            checkCustomerInFloor();

            LiftSim();

            building.getTimer().addSecond();
        };

        liftMiu=new KeyFrame(Duration.millis(1000),eventHandler);
    }


    /**
     * 电梯模拟实现逻辑代码
     */
    private void LiftSim(){
        Lift lift=building.getLift();
        if(building.isAllFloorNullWait() && lift.getCustomers().isEmpty()){
            lift.closeDoor();
            lift.stop();
        }else {
            switch (lift.getState()) {
                case Lift.LIFT_STOP:
                    if (lift.getStateLD()==Lift.LIFT_DISCHARGE_CUSTOMER){
                        if (!custoemrGoOutLift()) {
                            lift.setStateLD(Lift.LIFT_LOADING_CUSTOMER);
                        }
                    }

                    if (lift.getStateLD()==Lift.LIFT_LOADING_CUSTOMER){
                        if (!customerGetInLift()) {
                            lift.setStateLD(Lift.LIFT_DISCHARGE_CUSTOMER);
                            lift.run();
                        }
                    }
                    break;
                case Lift.LIFT_RUNNING:
                    lift.closeDoor();
                    if (liftClock < 10) {
                        liftClock++;
                    } else {
                        switch (lift.getDirection()) {
                            case Lift.LIFT_UP:
                                lift.setNowLevel(lift.getNowLevel() + 1);
                                System.out.println("当前楼层：" + lift.getNowLevel());
                                if ((lift.getCustomers().isEmpty() && building.isUpNullWaitFromThisFloor(lift.getNowLevel()))) {
                                    lift.turnDown();
                                }
                                break;
                            case Lift.LIFT_DOWN:
                                lift.setNowLevel(lift.getNowLevel() - 1);
                                System.out.println("当前楼层：" + lift.getNowLevel());
                                if (lift.getCustomers().isEmpty() && building.isDownNullWaitFromThisFloor(lift.getNowLevel())) {
                                    lift.turnUp();
                                }
                                break;
                        }


                        if (!lift.getCustomers().isEmpty() && (lift.getCustomers().peek().getDestinationFloor()==lift.getNowLevel()) ||
                                (lift.getDirection()==Lift.LIFT_UP && !building.getFloors().get(lift.getNowLevel()-1).isUpEmpty()) ||
                                (lift.getDirection()==Lift.LIFT_DOWN && !building.getFloors().get(lift.getNowLevel()-1).isDownEmpty())){
                            lift.stop();
                        }
                        liftClock = 0;
                    }
                    break;
            }
        }
    }

    /**
     * 扫描顾客
     * 当时间钟Timer的值与顾客到达时间相同
     * 将顾客放入到相应的楼层中。
     */
    private void checkCustomerInFloor(){
        Customer customer;
        do{
            if(customersQueue.isEmpty()){
                break;
            }
            customer=customersQueue.peek();
            if(customer.getComingTime()==building.getTimer().getSecond()){
                building.getFloors().get(customer.getSourceFloor()-1).inFLoor(customer);
                customersQueue.poll();
            }
        }while(customer.getComingTime()==building.getTimer().getSecond());
    }

    /**
     *顾客下电梯 模拟业务
     * 如果返回 True，证明下了乘客
     * @return
     */
    private boolean custoemrGoOutLift(){
        Lift lift=building.getLift();
        boolean hadCustomerOutLift=false;
        Customer customer=lift.outLift();
        if (customer!=null){
            customer.setLeavingTime(building.getTimer().getSecond()+1);
            hadCustomerOutLift=true;
        }

        return hadCustomerOutLift;
    }

    /**
     *顾客上电梯 模拟业务
     * 如果返回 True，证明上了乘客
     * @return
     */
    private boolean customerGetInLift(){
        Lift lift=building.getLift();
        boolean hadCustomerOutLift=lift.inLift(building.getFloors().get(lift.getNowLevel()-1));
        return  hadCustomerOutLift;
    }
}
