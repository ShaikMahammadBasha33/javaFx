package com.epam.library;


import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class BooksController {
    @FXML private TableView<Book> booksTable;
    @FXML private TableColumn<Book, String> titleColumn;
    @FXML private TableColumn<Book, String> authorColumn;
    @FXML private TableColumn<Book, Boolean> availableColumn;

    @FXML private TextField titleField;
    @FXML private TextField authorField;

    @FXML
    public void initialize() {
        titleColumn.setCellValueFactory(data -> data.getValue().titleProperty());
        authorColumn.setCellValueFactory(data -> data.getValue().authorProperty());
        availableColumn.setCellValueFactory(data -> data.getValue().availableProperty());

        booksTable.setItems(DataStore.books);
    }

    @FXML
    public void addBook() {
        String title = titleField.getText().trim();
        String author = authorField.getText().trim();

        if (!title.isEmpty() && !author.isEmpty()) {
            DataStore.books.add(new Book(title, author));
            titleField.clear();
            authorField.clear();
        }
    }

    @FXML
    public void deleteBook() {
        Book selected = booksTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            DataStore.books.remove(selected);
        }
    }
    
    @FXML
    public void saveBooks() {
        FileHandler.saveBooks(DataStore.books);
    }

    @FXML
	public void loadBooks() {
        List<Book> loadedBooks = FileHandler.loadBooks();
        if (loadedBooks != null) {
            DataStore.books.setAll(loadedBooks);
        }
    }

	public TableView<Book> getBooksTable() {
		return booksTable;
	}

	public void setBooksTable(TableView<Book> booksTable) {
		this.booksTable = booksTable;
	}

	public TableColumn<Book, String> getTitleColumn() {
		return titleColumn;
	}

	public void setTitleColumn(TableColumn<Book, String> titleColumn) {
		this.titleColumn = titleColumn;
	}

	public TableColumn<Book, String> getAuthorColumn() {
		return authorColumn;
	}

	public void setAuthorColumn(TableColumn<Book, String> authorColumn) {
		this.authorColumn = authorColumn;
	}

	public TableColumn<Book, Boolean> getAvailableColumn() {
		return availableColumn;
	}

	public void setAvailableColumn(TableColumn<Book, Boolean> availableColumn) {
		this.availableColumn = availableColumn;
	}

	public TextField getTitleField() {
		return titleField;
	}

	public void setTitleField(TextField titleField) {
		this.titleField = titleField;
	}

	public TextField getAuthorField() {
		return authorField;
	}

	public void setAuthorField(TextField authorField) {
		this.authorField = authorField;
	}
    
    



}
