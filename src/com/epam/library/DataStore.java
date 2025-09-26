package com.epam.library;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataStore {
    public static final ObservableList<Book> books = FXCollections.observableArrayList();
    public static final ObservableList<Person> persons = FXCollections.observableArrayList();

    static {
        // Predefined Books
        books.addAll(
                new Book("The Great Gatsby", "F. Scott Fitzgerald"),
                new Book("To Kill a Mockingbird", "Harper Lee"),
                new Book("1984", "George Orwell"),
                new Book("Moby Dick", "Herman Melville"),
                new Book("Pride and Prejudice", "Jane Austen")
        );

        // Predefined Persons
        persons.addAll(
                new Person("Alice Johnson", "alice_johnson@gmail.com"),
                new Person("Bob Smith", "bob_smith@gmail.com"),
                new Person("Charlie Brown", "charlie_brown@gmail.com"),
                new Person("Diana Prince", "diana_prince@gmail.com"),
                new Person("Ethan Hunt", "ethan_hunt@gmail.com")
        );
    }
}
