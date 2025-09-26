package com.epam.library;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BooksControllerTest extends JavaFXTestBase {

    private BooksController controller;
//    
//    @BeforeClass
//    public void initToolkit() {
//        try {
//            javafx.application.Platform.startup(() -> {});
//        } catch (IllegalStateException e) {
//            // already initialized
//        }
//    }


    @BeforeMethod
    public void setup() {
        // Clear data store
        DataStore.books.clear();

        // Initialize controller
        controller = new BooksController();

        // Mock FXML fields
        controller.setBooksTable(new TableView<>());
        controller.setTitleField(new TextField());
        controller.setAuthorField(new TextField());
        controller.setTitleColumn(new TableColumn<>());
        controller.setAuthorColumn(new TableColumn<>());
        controller.setAvailableColumn(new TableColumn<>());

        // Initialize controller (binds columns to properties)
        controller.initialize();
    }

    @Test
    public void testAddBook() {
        controller.getTitleField().setText("Book 1");
        controller.getAuthorField().setText("Author 1");

        controller.addBook();

        ObservableList<Book> books = DataStore.books;
        Assert.assertEquals(books.size(), 1);
        Assert.assertEquals(books.get(0).getTitle(), "Book 1");
        Assert.assertEquals(books.get(0).getAuthor(), "Author 1");
        Assert.assertTrue(books.get(0).isAvailable());
    }

    @Test
    public void testDeleteBook() {
        Book book = new Book("Book 2", "Author 2");
        DataStore.books.add(book);

        // Add to mock TableView selection
        controller.getBooksTable().setItems(FXCollections.observableArrayList(book));
        controller.getBooksTable().getSelectionModel().select(book);

        controller.deleteBook();

        Assert.assertFalse(DataStore.books.contains(book));
    }
}
