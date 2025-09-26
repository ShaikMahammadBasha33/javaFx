	package com.epam.library;
	
	import javafx.collections.FXCollections;
	import javafx.collections.ObservableList;
	import javafx.fxml.FXML;
	import javafx.scene.control.*;
	
	import java.time.LocalDate;
	import java.util.Optional;
	
	public class BorrowBooksController {
	
		// === FXML Elements ===
		@FXML
		private ComboBox<Person> personComboBox;
		@FXML
		private TableView<Book> booksTable;
		@FXML
		private TableColumn<Book, String> titleColumn;
		@FXML
		private TableColumn<Book, String> authorColumn;
		@FXML
		private TableColumn<Book, Boolean> availableColumn;
	
		@FXML
		private TableView<BorrowRecord> borrowedTable;
		@FXML
		private TableColumn<BorrowRecord, String> borrowedBookCol;
		@FXML
		private TableColumn<BorrowRecord, String> borrowDateCol;
		@FXML
		private TableColumn<BorrowRecord, String> expiryDateCol;
		@FXML
		private TableColumn<BorrowRecord, String> returnDateCol;
	
		@FXML
		private Button borrowButton;
		@FXML
		private Button returnButton;
	
		// === Person Details Panel ===
		@FXML
		private Label personNameLabel;
		@FXML
		private Label personEmailLabel;
		@FXML
		private Label borrowedCountLabel;
	
		@FXML
		public void initialize() {
			// === Person ComboBox Initialization ===
			personComboBox.setItems(DataStore.persons);
			personComboBox.setOnAction(e -> {
				updateBorrowedBooksTable();
				updatePersonDetails();
			});
	
			// === Books Table ===
			titleColumn.setCellValueFactory(data -> data.getValue().titleProperty());
			authorColumn.setCellValueFactory(data -> data.getValue().authorProperty());
			availableColumn.setCellValueFactory(data -> data.getValue().availableProperty());
			booksTable.setItems(DataStore.books);
	
			// === Borrowed Books Table ===
			borrowedBookCol.setCellValueFactory(data -> data.getValue().bookTitleProperty());
			borrowDateCol.setCellValueFactory(data -> data.getValue().borrowDateProperty());
			expiryDateCol.setCellValueFactory(data -> data.getValue().expiryDateProperty());
			returnDateCol.setCellValueFactory(data -> data.getValue().returnDateProperty());
		}
	
		// === Borrow Book ===
		@FXML
		public void borrowBook() {
		    Person selectedPerson = personComboBox.getValue();
		    Book selectedBook = booksTable.getSelectionModel().getSelectedItem();
	
		    if (selectedPerson == null || selectedBook == null) {
		        // JavaFX pop-up
		        Alert alert = new Alert(Alert.AlertType.WARNING);
		        alert.setTitle("Selection Required");
		        alert.setHeaderText(null);
		        alert.setContentText("Please select a person and a book to borrow.");
		        alert.showAndWait();
		        return;
		    }
	
		    if (!selectedBook.isAvailable()) {
		        Alert alert = new Alert(Alert.AlertType.WARNING);
		        alert.setTitle("Book Unavailable");
		        alert.setHeaderText(null);
		        alert.setContentText("This book is already borrowed.");
		        alert.showAndWait();
		        return;
		    }
	
		    BorrowRecord record = new BorrowRecord(selectedPerson, selectedBook);
		    selectedPerson.getBorrowedBooks().add(record);
		    selectedBook.setAvailable(false);
	
		    updateBorrowedBooksTable();
		    updatePersonDetails();
		}
	
	
		// === Return Book ===
		@FXML
		public void returnBook() {
		    Person selectedPerson = personComboBox.getValue();
		    BorrowRecord selectedRecord = borrowedTable.getSelectionModel().getSelectedItem();
	
		    if (selectedPerson == null || selectedRecord == null) {
		        Alert alert = new Alert(Alert.AlertType.WARNING);
		        alert.setTitle("Selection Required");
		        alert.setHeaderText(null);
		        alert.setContentText("Please select a person and a borrowed book to return.");
		        alert.showAndWait();
		        return;
		    }
	
		    Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
		    confirmation.setTitle("Confirm Return");
		    confirmation.setHeaderText(null);
		    confirmation.setContentText("Are you sure you want to return this book?");
		    Optional<ButtonType> result = confirmation.showAndWait();
	
		    if (result.isPresent() && result.get() == ButtonType.OK) {
		        Book book = selectedRecord.getBook();
		        selectedRecord.setReturnDate(LocalDate.now());
		        book.setAvailable(true);
	
		        updateBorrowedBooksTable();
		        updatePersonDetails();
		    }
		}
	
	
	
		private void updateBorrowedBooksTable() {
		    Person selectedPerson = personComboBox.getValue();
		    if (selectedPerson != null) {
		        ObservableList<BorrowRecord> activeBorrows = FXCollections.observableArrayList();
		        for (BorrowRecord record : selectedPerson.getBorrowedBooks()) {
		            if (record.getReturnDate() == null) {
		                activeBorrows.add(record);
		            }
		        }
		        borrowedTable.setItems(activeBorrows);
		    } else {
		        borrowedTable.setItems(FXCollections.observableArrayList());
		    }
		    borrowedTable.refresh();
		}
	
	
		// === Update Person Details Panel ===
		private void updatePersonDetails() {
			Person selectedPerson = personComboBox.getValue();
			if (selectedPerson != null) {
				personNameLabel.setText("Name: " + selectedPerson.getName());
				personEmailLabel.setText("Email: " + selectedPerson.getEmail());
				long activeBorrows = selectedPerson.getBorrowedBooks().stream().filter(r -> r.getReturnDate() == null)
						.count();
				borrowedCountLabel.setText("Borrowed Books: " + activeBorrows);
			} else {
				personNameLabel.setText("Name: -");
				personEmailLabel.setText("Email: -");
				borrowedCountLabel.setText("Borrowed Books: 0");
			}
		}

		public ComboBox<Person> getPersonComboBox() {
			return personComboBox;
		}

		public void setPersonComboBox(ComboBox<Person> personComboBox) {
			this.personComboBox = personComboBox;
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

		public TableView<BorrowRecord> getBorrowedTable() {
			return borrowedTable;
		}

		public void setBorrowedTable(TableView<BorrowRecord> borrowedTable) {
			this.borrowedTable = borrowedTable;
		}



		public void setBorrowedBookCol(TableColumn<BorrowRecord, String> borrowedBookCol) {
			this.borrowedBookCol = borrowedBookCol;
		}


		public void setBorrowDateCol(TableColumn<BorrowRecord, String> borrowDateCol) {
			this.borrowDateCol = borrowDateCol;
		}

		public void setExpiryDateCol(TableColumn<BorrowRecord, String> expiryDateCol) {
			this.expiryDateCol = expiryDateCol;
		}


		public void setReturnDateCol(TableColumn<BorrowRecord, String> returnDateCol) {
			this.returnDateCol = returnDateCol;
		}


		public void setBorrowedCountLabel(Label borrowedCountLabel) {
			this.borrowedCountLabel = borrowedCountLabel;
		}
		
		
	}
