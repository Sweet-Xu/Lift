package lift;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lift.model.customer.Customer;
import lift.view.Controller;

import java.io.IOException;

public class Main extends Application {

    private BorderPane primaryPane;

    private ObservableList<Customer> customersData=FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception{
        showLiftMainOverView();
        primaryStage.setTitle("电梯模拟");
        primaryStage.setScene(new Scene(primaryPane));
        primaryStage.show();
    }

    public void showLiftMainOverView(){
        try{
            FXMLLoader fxmlLoader=new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/lift/view/LiftApp.fxml"));
            primaryPane=fxmlLoader.load();

            Controller controller=fxmlLoader.getController();
            controller.setMainApp(this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }

    public ObservableList<Customer> getCustomersData() {
        return customersData;
    }
}
