package layouts.gridlayout;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GridPaneExample extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("GridPaneExample.fxml"));
        Scene scene = new Scene(root, 300, 200);
        scene.getStylesheets().add(getClass().getResource("gridpane.css").toExternalForm());
        stage.setTitle("GridPane Example");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

