package control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import control.Bridge;

public class LoginController {
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

    }

    @FXML
    private void login(){
        String password = passwordTextField.getText();
        String username = userTextField.getText();
    }
    

}
