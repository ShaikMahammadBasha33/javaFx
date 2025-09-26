package com.epam.library;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BorrowRecord {
    private final Person person;
    private final Book book;
    private final LocalDate borrowDate;
    private LocalDate returnDate;
    private final LocalDate expiryDate;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public BorrowRecord(Person person, Book book) {
        this.person = person;
        this.book = book;
        this.borrowDate = LocalDate.now();
        this.expiryDate = borrowDate.plusMonths(1);
    }

    public Person getPerson() { return person; }
    public Book getBook() { return book; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public LocalDate getExpiryDate() { return expiryDate; }

    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }

    // Bindings for TableView
    public StringProperty bookTitleProperty() {
        return new SimpleStringProperty(book.getTitle());
    }

    public StringProperty borrowDateProperty() {
        return new SimpleStringProperty(borrowDate.format(FORMATTER));
    }

    public StringProperty expiryDateProperty() {
        return new SimpleStringProperty(expiryDate.format(FORMATTER));
    }

    public StringProperty returnDateProperty() {
        return new SimpleStringProperty(returnDate == null ? "Not Returned" : returnDate.format(FORMATTER));
    }
}
