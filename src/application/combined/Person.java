package application.combined;


import javafx.beans.property.*;

public class Person {
    private final StringProperty name;
    private final IntegerProperty age;

    public Person(String name, int age) {
        this.name = new SimpleStringProperty(name);
        this.age = new SimpleIntegerProperty(age);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public IntegerProperty ageProperty() {
        return age;
    }
    public String getName() {
		return name.get();
	}
    public int getAge() {
		return age.get();
	}
}

