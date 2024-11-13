package control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private Label errorPrompt;
    @FXML
    private TextArea userTextField;
    @FXML
    private TextArea passwordTextField;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    @FXML
    private URL location;
    @FXML
    private ResourceBundle resources;

    public LoginController(){

    }

    @FXML
    private void initialize(){
        errorPrompt.setVisible(false);
    }

    @FXML
    private void login(){
        Bridge bridge = new Bridge();
        String password = passwordTextField.getText();
        String username = userTextField.getText();
        try {
            if(bridge.getMatchingAccount(username,password, errorPrompt)){
                Stage stage = (Stage) loginButton.getScene().getWindow();
                SceneSwitcher sceneSwitcher = new SceneSwitcher();
                sceneSwitcher.swap(stage.getScene(), "landing");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void registerBtn(){
        try {
                Stage stage = (Stage) loginButton.getScene().getWindow();
                SceneSwitcher sceneSwitcher = new SceneSwitcher();
                sceneSwitcher.swap(stage.getScene(), "register");
        } catch (IOException e) {
            e.printStackTrace();
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

    public Button getLoginButton() {
        return loginButton;
    }

    public void setLoginButton(Button loginButton) {
        this.loginButton = loginButton;
    }

    public Button getRegisterButton() {
        return registerButton;
    }

    public void setRegisterButton(Button registerButton) {
        this.registerButton = registerButton;
    }

    public Label getErrorPrompt() {
        return errorPrompt;
    }

    public void setErrorPrompt(Label errorPrompt) {
        this.errorPrompt = errorPrompt;
    }
}
