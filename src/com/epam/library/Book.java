package com.epam.library;

import javafx.beans.property.*;

public class Book {
	private final StringProperty title = new SimpleStringProperty();
    private final StringProperty author = new SimpleStringProperty();
    private final BooleanProperty available = new SimpleBooleanProperty(true);

    public Book(String title, String author) {
        this.title.set(title);
        this.author.set(author);
        this.available.set(true);
    }

    public String getTitle() { return title.get(); }
    public void setTitle(String title) { this.title.set(title); }
    public StringProperty titleProperty() { return title; }

    public String getAuthor() { return author.get(); }
    public void setAuthor(String author) { this.author.set(author); }
    public StringProperty authorProperty() { return author; }

    public boolean isAvailable() { return available.get(); }
    public void setAvailable(boolean available) { this.available.set(available); }
    public BooleanProperty availableProperty() { return available; }

    @Override
    public String toString() {
        return getTitle() + " by " + getAuthor();
    }
}
