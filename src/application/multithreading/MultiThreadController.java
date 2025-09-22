package application.multithreading;


import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class MultiThreadController {

    @FXML
    private Label statusLabel;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Button startButton;

    @FXML
    private void startTask() {
        startButton.setDisable(true);

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                for (int i = 1; i <= 10; i++) {
                    Thread.sleep(500); // simulate work
                    updateProgress(i, 10);
                    updateMessage("Step " + i + " of 10 completed");
                }
                return null;
            }
        };

        progressBar.progressProperty().bind(task.progressProperty());
        statusLabel.textProperty().bind(task.messageProperty());

        task.setOnSucceeded(e -> {
            statusLabel.textProperty().unbind();
            statusLabel.setText("Task Completed ✅");
            startButton.setDisable(false);
        });

        // Start task in background thread
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }
}

