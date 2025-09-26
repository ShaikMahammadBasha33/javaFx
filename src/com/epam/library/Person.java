package com.epam.library;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Person {
	private final StringProperty name = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();

    private final ObservableList<BorrowRecord> borrowedBooks = FXCollections.observableArrayList();

    public Person(String name, String email) {
        this.name.set(name);
        this.email.set(email);
    }

    public String getName() { return name.get(); }
    public StringProperty nameProperty() { return name; }

    public String getEmail() { return email.get(); }
    public StringProperty emailProperty() { return email; }

    public ObservableList<BorrowRecord> getBorrowedBooks() {
        return borrowedBooks;
    }

    @Override
    public String toString() {
        return getName();
    }
}
