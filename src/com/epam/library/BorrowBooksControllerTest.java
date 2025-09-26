package com.epam.library;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class BorrowBooksControllerTest extends JavaFXTestBase {

    private BorrowBooksController controller;
    private Person person;
    private Book book;

    @BeforeClass
    public void setup() throws InterruptedException {
        DataStore.persons.clear();
        DataStore.books.clear();

        controller = new BorrowBooksController();

        controller.setPersonComboBox(new ComboBox<>());
        controller.setBooksTable(new TableView<>());
        controller.setBorrowedTable(new TableView<>());
        controller.setBorrowedCountLabel(new Label());

        controller.setTitleColumn(new TableColumn<>());
        controller.setAuthorColumn(new TableColumn<>());
        controller.setAvailableColumn(new TableColumn<>());

        controller.setBorrowedBookCol(new TableColumn<>());
        controller.setBorrowDateCol(new TableColumn<>());
        controller.setExpiryDateCol(new TableColumn<>());
        controller.setReturnDateCol(new TableColumn<>());

        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            controller.initialize();

            person = new Person("Bob", "bob@example.com");
            book = new Book("JavaFX", "AuthorX");

            DataStore.persons.add(person);
            DataStore.books.add(book);

            controller.getPersonComboBox().setItems(DataStore.persons);
            controller.getBooksTable().setItems(DataStore.books);

            latch.countDown();
        });

        latch.await(5, TimeUnit.SECONDS);
    }

    @Test
    public void testBorrowBook() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            controller.getPersonComboBox().getSelectionModel().select(person);
            controller.getBooksTable().getSelectionModel().select(book);

            controller.borrowBook();
            latch.countDown();
        });

        latch.await(5, TimeUnit.SECONDS);

        Assert.assertEquals(person.getBorrowedBooks().size(), 1);
        Assert.assertFalse(book.isAvailable());
    }

    @Test
    public void testReturnBook() throws InterruptedException {
        BorrowRecord record = new BorrowRecord(person, book);
        person.getBorrowedBooks().add(record);
        book.setAvailable(false);

        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            controller.getPersonComboBox().getSelectionModel().select(person);
            controller.getBorrowedTable().setItems(FXCollections.observableArrayList(record));
            controller.getBorrowedTable().getSelectionModel().select(record);

            record.setReturnDate(null);
            controller.returnBook();
            latch.countDown();
        });

        latch.await(5, TimeUnit.SECONDS);

        Assert.assertTrue(book.isAvailable());
        Assert.assertNotNull(record.getReturnDate());
    }
}
