package application.combined;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

public class CombinedController implements Initializable {

    @FXML
    private TreeView<String> treeView;

    @FXML
    private ListView<String> listView;

    @FXML
    private TableView<Person> tableView;
    @FXML
    private TableColumn<Person, String> colName;
    @FXML
    private TableColumn<Person, Integer> colAge;

    @SuppressWarnings("unchecked")
	@Override
    public void initialize(URL location, ResourceBundle resources) {
        TreeItem<String> root = new TreeItem<>("Programming Languages");
        TreeItem<String> parent1 = new TreeItem<>("Java");
        TreeItem<String> parent2 = new TreeItem<>("C");
        root.getChildren().addAll(parent1, parent2);
        parent1.getChildren().addAll(new TreeItem<>("Opps"), new TreeItem<>("javaFX"));
        parent2.getChildren().addAll(new TreeItem<>("Pointers"), new TreeItem<>("Functions"));
        root.setExpanded(true);
        treeView.setRoot(root);

        ObservableList<String> items = FXCollections.observableArrayList("Java", "Python", "C","C++");
        listView.setItems(items);

        colName.setCellValueFactory(data -> data.getValue().nameProperty());
        colAge.setCellValueFactory(data -> data.getValue().ageProperty().asObject());

        ObservableList<Person> people = FXCollections.observableArrayList(
                new Person("Mahammad", 25),
                new Person("Shaik", 30),
                new Person("Basha", 28)
        );
        tableView.setItems(people);
    }
    
    @FXML
    private void saveData() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Combined Data");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        File file = fileChooser.showSaveDialog(tableView.getScene().getWindow());
        if (file != null) {
            try (FileWriter writer = new FileWriter(file)) {
                writer.write("TreeView Data:\n");
                saveTreeItems(treeView.getRoot(), writer, "");

                writer.write("\nListView Data:\n");
                for (String item : listView.getItems()) {
                    writer.write(item + "\n");
                }

                writer.write("\nTableView Data:\n");
                for (Person p : tableView.getItems()) {
                    writer.write(p.getName() + " - " + p.getAge() + "\n");
                }

                new Alert(Alert.AlertType.INFORMATION, "Data saved successfully!").showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Error saving file!").showAndWait();
            }
        }
    }

    private void saveTreeItems(TreeItem<String> item, FileWriter writer, String indent) throws IOException {
        if (item != null) {
            writer.write(indent + item.getValue() + "\n");
            for (TreeItem<String> child : item.getChildren()) {
                saveTreeItems(child, writer, indent + "  ");
            }
        }
    }
}

