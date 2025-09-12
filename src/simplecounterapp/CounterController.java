package simplecounterapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CounterController {
    @FXML
    private Label counterLabel;

    private Counter model = new Counter();

    @FXML
    public void handleIncrement() {
        model.increment();
        updateView();
    }
    
    @FXML
    public void handleDecrement() {
		model.decrement();
		updateView();
	}

    @FXML
    public void handleReset() {
        model.reset();
        updateView();
    }

    private void updateView() {
        counterLabel.setText(String.valueOf(model.getCount()));
    }
}
