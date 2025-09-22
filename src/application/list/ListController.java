package application.list;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class ListController implements Initializable {
	
	@FXML
	private ListView<String> listView;
	
	private ObservableList<String> names;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		names = FXCollections.observableArrayList(
				"mahammad",
				"shaik",
				"basha"
				
				);
		
		listView.setItems(names);
		
		
		
	}
	

}
