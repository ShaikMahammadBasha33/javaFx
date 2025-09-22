package application.tree;

import javafx.application.Application;
import javafx.stage.Stage;

public class TreeViewExample extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		javafx.scene.Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("tree.fxml"));
		javafx.scene.Scene scene = new javafx.scene.Scene(root);
		primaryStage.setTitle("TreeView Example");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

}
