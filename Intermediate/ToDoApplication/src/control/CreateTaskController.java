package control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import model.Accounts;
import model.Task;
import org.xml.sax.SAXException;

public class CreateTaskController {
    @FXML
    private TextArea errorPrompt;
    @FXML
    private TextArea titleTextField;
    @FXML
    private Button logoutBtn;
    @FXML
    private DatePicker datePickField;
    @FXML
    private MenuButton importancePickField;
    @FXML
    private TextArea descriptionBox;
    @FXML
    private Button createTaskBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;

    private Accounts currAccount;

    public CreateTaskController(){}

    public void initialize(){
        UserManagement userManagement = UserManagement.getInstance();
        currAccount = userManagement.getAccount();

        errorPrompt.setVisible(false);
    }

    @FXML
    private void createAcc(){
        if(titleTextField.getText().isEmpty() || datePickField.getValue() == null || 
        importancePickField.getText().isEmpty() || descriptionBox.getText().isEmpty()){
            Paint paint = Paint.valueOf("DARKRED");
            Border b = new Border(new BorderStroke(paint,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,null));
            Border plain = new Border(new BorderStroke(null,null,null,null));

            

            if(titleTextField.getText().isEmpty()){
                titleTextField.setBorder(b);
            } else {
                titleTextField.setBorder(plain);
            }

            if(datePickField.getValue() == null){
                datePickField.setBorder(b);
            } else {
                datePickField.setBorder(plain);
            }

            if(importancePickField.getText().isEmpty()){
                importancePickField.setBorder(b);
            } else {
                importancePickField.setBorder(plain);
            }

            if(descriptionBox.getText().isEmpty()){
                descriptionBox.setBorder(b);
            } else {
                descriptionBox.setBorder(plain);
            }

            errorPrompt.setText("Empty Values Found");
            errorPrompt.setVisible(true);

        } else {
            Task task = new Task(titleTextField.getText(), datePickField.getValue(), 
            importancePickField.getText(), descriptionBox.getText());

            Bridge bridge = new Bridge();
            try {
                SceneSwitcher sceneSwitcher = new SceneSwitcher();
                Stage stage = (Stage) createTaskBtn.getScene().getWindow();

                bridge.writeToFileTasks(currAccount.getUsername(), task);
                sceneSwitcher.swap(stage.getScene(), "landing");
            } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            }
        }
    }

    @FXML
    private void cancel() throws IOException{
        SceneSwitcher sceneSwitcher = new SceneSwitcher();
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        sceneSwitcher.swap(stage.getScene(), "landing");
    }

    @FXML
    private void logout() throws IOException{
        SceneSwitcher sceneSwitcher = new SceneSwitcher();
        Stage stage = (Stage) logoutBtn.getScene().getWindow();
        sceneSwitcher.swap(stage.getScene(), "login");
    }

    public TextArea getTitleTextField() {
        return titleTextField;
    }

    public void setTitleTextField(TextArea titleTextField) {
        this.titleTextField = titleTextField;
    }

    public Button getLogoutBtn() {
        return logoutBtn;
    }
    public void setLogoutBtn(Button logoutBtn) {
        this.logoutBtn = logoutBtn;
    }
    public DatePicker getDatePickField() {
        return datePickField;
    }
    public void setDatePickField(DatePicker datePickField) {
        this.datePickField = datePickField;
    }
    public MenuButton getImportancePickField() {
        return importancePickField;
    }
    public void setImportancePickField(MenuButton importancePickField) {
        this.importancePickField = importancePickField;
    }
    public TextArea getDescriptionBox() {
        return descriptionBox;
    }
    public void setDescriptionBox(TextArea descriptionBox) {
        this.descriptionBox = descriptionBox;
    }
    public Button getCreateTaskBtn() {
        return createTaskBtn;
    }
    public void setCreateTaskBtn(Button createTaskBtn) {
        this.createTaskBtn = createTaskBtn;
    }
    public Button getCancelBtn() {
        return cancelBtn;
    }
    public void setCancelBtn(Button cancelBtn) {
        this.cancelBtn = cancelBtn;
    }
}
