package application.list;

import javafx.application.Application;

public class ListViewExample extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(javafx.stage.Stage primaryStage) throws Exception {
		javafx.scene.Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("list.fxml"));
		javafx.scene.Scene scene = new javafx.scene.Scene(root);
		primaryStage.setTitle("ListView Example");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

}
