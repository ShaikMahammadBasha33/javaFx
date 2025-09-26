package com.epam.library;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class PersonsControllerTest extends JavaFXTestBase {

    private PersonsController controller;

    @BeforeClass
    public void setup() throws Exception {
        DataStore.persons.clear();
        

        controller = new PersonsController();
        controller.setNameField(new TextField());
        controller.setEmailField(new TextField());
        controller.setNameColumn(new TableColumn<>());
        controller.setEmailColumn(new TableColumn<>());
        controller.setPersonsTable(new TableView<>());

        // Run initialize on FX thread
        runOnFxThreadAndWait(controller::initialize);
    }


    @Test
    public void testDeletePerson() throws InterruptedException {
        Person person = new Person("Alice", "alice@example.com");

        runOnFxThreadAndWait(() -> {
            DataStore.persons.add(person);
            controller.getPersonsTable().getItems().add(person);
            controller.getPersonsTable().getSelectionModel().select(person);
            controller.deletePerson();
        });

        Assert.assertFalse(DataStore.persons.contains(person), "Person should be deleted");
    }

    /**
     * Utility method to run tasks on the JavaFX thread and wait for completion.
     */
    private void runOnFxThreadAndWait(Runnable action) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try {
                action.run();
            } finally {
                latch.countDown();
            }
        });
        latch.await(5, TimeUnit.SECONDS);
    }
}
