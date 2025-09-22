package application.table;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TableViewExample extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("table.fxml"));
		Scene scene = new Scene(root);
		arg0.setTitle("TableView Example");
		arg0.setScene(scene);
		arg0.show();
		
	}

}
