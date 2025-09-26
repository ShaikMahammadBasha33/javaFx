package com.epam.library;


import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PersonsController {
    @FXML private TableView<Person> personsTable;
    @FXML private TableColumn<Person, String> nameColumn;
    @FXML private TableColumn<Person, String> emailColumn;

    @FXML private TextField nameField;
    @FXML private TextField emailField;

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(data -> data.getValue().nameProperty());
        emailColumn.setCellValueFactory(data -> data.getValue().emailProperty());

        personsTable.setItems(DataStore.persons);
    }

    @FXML
    public void addPerson() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();

        if (!name.isEmpty() && !email.isEmpty()) {
            DataStore.persons.add(new Person(name, email));
            nameField.clear();
            emailField.clear();
        }
    }

    @FXML
    public void deletePerson() {
        Person selected = personsTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            DataStore.persons.remove(selected);
        }
    }
    
    @FXML
    public void savePersons() {
        FileHandler.savePersons(DataStore.persons);
    }

    @FXML
    private void loadPersons() {
        List<Person> loadedPersons = FileHandler.loadPersons();
        if (loadedPersons != null) {
            DataStore.persons.setAll(loadedPersons);
        }
    }

	public TableView<Person> getPersonsTable() {
		return personsTable;
	}

	public void setPersonsTable(TableView<Person> personsTable) {
		this.personsTable = personsTable;
	}

	public TableColumn<Person, String> getNameColumn() {
		return nameColumn;
	}

	public void setNameColumn(TableColumn<Person, String> nameColumn) {
		this.nameColumn = nameColumn;
	}

//	public TableColumn<Person, String> getEmailColumn() {
//		return emailColumn;
//	}

	public void setEmailColumn(TableColumn<Person, String> emailColumn) {
		this.emailColumn = emailColumn;
	}

	public TextField getNameField() {
		return nameField;
	}

	public void setNameField(TextField nameField) {
		this.nameField = nameField;
	}

	public TextField getEmailField() {
		return emailField;
	}

	public void setEmailField(TextField emailField) {
		this.emailField = emailField;
	}

	public void setBooksTable(TableView tableView) {
		this.personsTable = tableView;
		// TODO Auto-generated method stub
		
	}
    
    

}
