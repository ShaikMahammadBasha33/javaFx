package layouts.layoutdashboard;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DashBoardUsingLayouts extends Application {

	@Override
	public void start(Stage primaryStage) {
		BorderPane borderPane = new BorderPane();

		// Top – Header
		Label header = new Label("My Dashboard");
		header.setStyle("-fx-font-size: 24px; -fx-padding: 10;");
		borderPane.setTop(header);

		// Bottom – Footer
		Label footer = new Label("© 2025 MyApp");
		footer.setStyle("-fx-padding: 10;");
		borderPane.setBottom(footer);

		// Left – Menu
		HBox menu = new HBox(10);
		menu.setPadding(new Insets(10));
		Button homeButton = new Button("Home");
		Button profileButton = new Button("Profile");
		Button settingsButton = new Button("Settings");
		menu.getChildren().addAll(homeButton, profileButton, settingsButton);
		borderPane.setLeft(menu);

		VBox statusBox = new VBox();
		statusBox.setPadding(new Insets(10));
		Label status = new Label("Status: Online");
		statusBox.getChildren().add(status);
		borderPane.setRight(statusBox);

		// Center – Main content
		Label mainContent = new Label("Welcome to the Dashboard!");
		mainContent.setStyle("-fx-font-size: 18px;");
		borderPane.setCenter(mainContent);
		BorderPane.setMargin(mainContent, new Insets(20));

		Scene scene = new Scene(borderPane, 600, 400);
		primaryStage.setTitle("BorderPane Example – Dashboard");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
