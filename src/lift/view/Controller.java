package lift.view;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lift.Main;
import lift.model.customer.Customer;
import lift.model.customer.ElderCustomer;
import lift.model.customer.YoungCustomer;
import lift.util.AlertUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    private Main mainApp;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bindTableColumns();
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

        });
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
            customersTableView.getItems().remove(selectedIndex);
            System.out.println("customers="+mainApp.getCustomersData().size());
        }
    }

    /**
     * 设置主类对象
     * @param mainApp
     */
    public void setMainApp(Main mainApp){
        this.mainApp=mainApp;

        customersTableView.setItems(mainApp.getCustomersData());
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
    }
}
