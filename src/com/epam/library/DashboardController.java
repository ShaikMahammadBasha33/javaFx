package com.epam.library;

import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DashboardController {
    private void loadPage(String fxml) {
        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/com/epam/library/" + fxml))));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openBooks() { loadPage("books.fxml"); }
    public void openPersons() { loadPage("persons.fxml"); }
    public void openBorrowBooks() { loadPage("borrow.fxml"); }
    
}
