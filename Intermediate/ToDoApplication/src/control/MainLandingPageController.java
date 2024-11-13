package control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Accounts;

public class MainLandingPageController {
    @FXML
    private Button logoutBtn;
    @FXML
    private TextArea calendarArea;
    @FXML
    private VBox textStorage;
    @FXML
    private Button createTask;
    @FXML
    private Button viewTask;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;

    private Accounts currAccount;
    private Bridge bridge;

    public MainLandingPageController(){

    }

    @FXML
    private void initialize(){
        UserManagement userManagement = UserManagement.getInstance();
        currAccount = userManagement.getAccount();

        bridge = new Bridge();
        bridge.loadTasks(currAccount, textStorage);
    }

    @FXML
    private void createTask() throws IOException{
        SceneSwitcher sceneSwitcher = new SceneSwitcher();
        Stage scene =  (Stage) logoutBtn.getScene().getWindow();

        sceneSwitcher.swap(scene.getScene(), "create");
    }

    @FXML
    private void viewTask() throws IOException {
        SceneSwitcher sceneSwitcher = new SceneSwitcher();
        Stage scene =  (Stage) logoutBtn.getScene().getWindow();

        sceneSwitcher.swap(scene.getScene(), "view");
    }

    @FXML
    private void logout() throws IOException{
        SceneSwitcher sceneSwitcher = new SceneSwitcher();
        Stage stage = (Stage) logoutBtn.getScene().getWindow();
        sceneSwitcher.swap(stage.getScene(), "login");
    }

    public Button getLogoutBtn() {
        return logoutBtn;
    }
    public void setLogoutBtn(Button logoutBtn) {
        this.logoutBtn = logoutBtn;
    }
    public TextArea getCalendarArea() {
        return calendarArea;
    }
    public void setCalendarArea(TextArea calendarArea) {
        this.calendarArea = calendarArea;
    }
    public VBox getTextStorage() {
        return textStorage;
    }
    public void setTextStorage(VBox textStorage) {
        this.textStorage = textStorage;
    }
    public Button getCreateTask() {
        return createTask;
    }
    public void setCreateTask(Button createTask) {
        this.createTask = createTask;
    }
    public Button getViewTask() {
        return viewTask;
    }
    public void setViewTask(Button viewTask) {
        this.viewTask = viewTask;
    }

    public Accounts getCurrAccount() {
        return currAccount;
    }

    public void setCurrAccount(Accounts currAccount) {
        this.currAccount = currAccount;
    }
}
