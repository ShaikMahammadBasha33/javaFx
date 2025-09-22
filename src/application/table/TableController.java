package application.table;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class TableController implements javafx.fxml.Initializable {
	
	@FXML
	private TableView<Student> tableview;
	
	@FXML
	private TableColumn<Student, String> namecolumn;
	
	@FXML
	private TableColumn<Student, Integer> agecolumn;
	
	@FXML
	private TableColumn<Student, String> addresscolumn;
	
	@FXML
	private ObservableList<Student> studentList;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		namecolumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		agecolumn.setCellValueFactory(new PropertyValueFactory<>("age"));
		addresscolumn.setCellValueFactory(new PropertyValueFactory<>("address"));
		
		studentList = FXCollections.observableArrayList(
				new Student("Mahammad", 25, "Hyderabad"),
				new Student("John Doe", 22, "New York"),
				new Student("Jane Smith", 23, "Los Angeles")
				);
		tableview.setEditable(true);
		namecolumn.setCellFactory(TextFieldTableCell.forTableColumn());
		namecolumn.setOnEditCommit(e -> {
			Student student = e.getRowValue();
			student.setName(e.getNewValue());
		});
		tableview.setItems(studentList);
		
		
	}
	
	
	
	 

	

}
