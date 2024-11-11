import java.io.FileInputStream;
import javafx.application.Application;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage arg0) throws Exception {
        FXMLLoader loader = new FXMLLoader();

        String fxmlLoginView = "src\\view\\LoginView.fxml";
        FileInputStream fxmlStream = new FileInputStream(fxmlLoginView);

        VBox root = (VBox) loader.load(fxmlStream);

        Scene scene = new Scene(root);
        arg0.setScene(scene);
        arg0.setTitle("To Do Application by Jay");
        arg0.show();
    }
    
}
