package layouts.stackpane;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StackPaneExample extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("StackPaneExample.fxml"));
        Scene scene = new Scene(root, 300, 200);
        scene.getStylesheets().add(getClass().getResource("stackpane.css").toExternalForm());
        stage.setTitle("StackPane Example");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
