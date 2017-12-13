package lift.util;

        import javafx.scene.control.Alert;

public class AlertUtil {
    Alert alert;

    public AlertUtil(Alert alert) {
        this.alert = alert;
    }

    public AlertUtil(Alert.AlertType type, String mesg){
        this.alert=new Alert(type,mesg);
    }

    public void showAlert(){
        this.alert.showAndWait();
    }
}
