package control;

import java.io.FileInputStream;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class SceneSwitcher {
    public void swap(Scene scene, String text) throws IOException{
        String fxmlLoginView = "src\\view\\";

        switch (text) {
            case "landing":
                fxmlLoginView += "MainLandingPage.fxml";
                break;
            case "register":
                fxmlLoginView += "Register.fxml";
                break;
            case "login":
                fxmlLoginView += "LoginView.fxml";
                break;
            case "create":
                fxmlLoginView += "CreateNewTask.fxml";
                break;
            case "view":
                fxmlLoginView += "ViewTasks.fxml";
                break;
                
        }

        FXMLLoader loader = new FXMLLoader();
        FileInputStream fxmlStream = new FileInputStream(fxmlLoginView);
        VBox root = (VBox) loader.load(fxmlStream);
        scene.setRoot(root);
    }
}
