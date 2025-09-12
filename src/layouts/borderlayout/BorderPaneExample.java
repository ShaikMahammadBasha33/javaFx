package layouts.borderlayout;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class BorderPaneExample extends Application {
    @Override
    public void start(Stage stage) {
        BorderPane borderPane = new BorderPane();

        borderPane.setTop(new Label("Top Area"));
        borderPane.setBottom(new Label("Bottom Area"));
        borderPane.setLeft(new Button("Left Button"));
        borderPane.setRight(new Button("Right Button"));
        borderPane.setCenter(new Label("Center Area"));

        BorderPane.setMargin(borderPane.getTop(), new Insets(10));
        BorderPane.setMargin(borderPane.getBottom(), new Insets(10));
        BorderPane.setMargin(borderPane.getLeft(), new Insets(10));
        BorderPane.setMargin(borderPane.getRight(), new Insets(10));

        Scene scene = new Scene(borderPane, 400, 300);
        stage.setTitle("BorderPane Example");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}