package control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

public class RegisterController {
    @FXML
    private TextArea userTextField;
    @FXML
    private TextArea passwordTextField;
    @FXML
    private TextArea cpasswordTextField;
    @FXML
    private Label errorPrompt;
    @FXML
    private Button createBtn;
    @FXML
    private Button returnBtn;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;

    @FXML
    private void createAccount() throws ParserConfigurationException, SAXException, IOException, TransformerException{
        Bridge bridge = new Bridge();

        if(!passwordTextField.getText().equals(cpasswordTextField.getText())){
            errorPrompt.setText("Password Does Not Match");
            errorPrompt.setVisible(true);
        } else {
            if(bridge.checkUser(userTextField.getText())){
                errorPrompt.setText("User Already Exists");
                errorPrompt.setVisible(true);
            } else{
                bridge.writeToFileAccounts(userTextField.getText(), passwordTextField.getText(), null);
                returnLogin();
            }
        }
    }

    @FXML
    private void returnLogin(){
        try {
            Stage stage = (Stage) returnBtn.getScene().getWindow();
            SceneSwitcher sceneSwitcher = new SceneSwitcher();
            sceneSwitcher.swap(stage.getScene(), "login");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public TextArea getUserTextField() {
        return userTextField;
    }

    public void setUserTextField(TextArea userTextField) {
        this.userTextField = userTextField;
    }

    public TextArea getPasswordTextField() {
        return passwordTextField;
    }

    public void setPasswordTextField(TextArea passwordTextField) {
        this.passwordTextField = passwordTextField;
    }

    public TextArea getCpasswordTextField() {
        return cpasswordTextField;
    }

    public void setCpasswordTextField(TextArea cpasswordTextField) {
        this.cpasswordTextField = cpasswordTextField;
    }

    public Button getCreateBtn() {
        return createBtn;
    }

    public void setCreateBtn(Button createBtn) {
        this.createBtn = createBtn;
    }

    public Button getReturnBtn() {
        return returnBtn;
    }

    public void setReturnBtn(Button returnBtn) {
        this.returnBtn = returnBtn;
    }
    
}
