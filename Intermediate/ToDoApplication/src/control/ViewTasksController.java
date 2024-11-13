package control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import model.Accounts;

public class ViewTasksController {
    @FXML
    private Button logoutBtn;
    @FXML
    private VBox taskStorage;
    @FXML
    private Button backBtn;
    @FXML
    private ResourceBundle resourceBundle;
    @FXML
    private URL location;

    private Accounts currAccount;

    public ViewTasksController(){

    }

    public void initialize(){
        UserManagement usermanagement = UserManagement.getInstance();
        currAccount = usermanagement.getAccount();
    }

    @FXML
    private void logout() throws IOException{
        SceneSwitcher sceneSwitcher = new SceneSwitcher();
        Stage stage = (Stage) logoutBtn.getScene().getWindow();
        sceneSwitcher.swap(stage.getScene(), "login");
    }

    @FXML
    private void back() throws IOException{
        SceneSwitcher sceneSwitcher = new SceneSwitcher();
        Stage stage = (Stage) logoutBtn.getScene().getWindow();
        sceneSwitcher.swap(stage.getScene(), "landing");
    }

    public Button getLogoutBtn() {
        return logoutBtn;
    }
    public void setLogoutBtn(Button logoutBtn) {
        this.logoutBtn = logoutBtn;
    }
    public VBox getTaskStorage() {
        return taskStorage;
    }
    public void setTaskStorage(VBox taskStorage) {
        this.taskStorage = taskStorage;
    }
    public Button getBackBtn() {
        return backBtn;
    }
    public void setBackBtn(Button backBtn) {
        this.backBtn = backBtn;
    }
    
    
}
