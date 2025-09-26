package com.epam.library;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

public class FileHandler {

    // Save Books to CSV (user chooses any file)
    public static void saveBooks(ObservableList<Book> books) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("CSV files", "csv"));
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            // Ensure file has .csv extension
            if (!file.getName().toLowerCase().endsWith(".csv")) {
                file = new File(file.getAbsolutePath() + ".csv");
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write("Title,Author,Available");
                writer.newLine();
                for (Book b : books) {
                    writer.write(b.getTitle() + "," + b.getAuthor() + "," + b.isAvailable());
                    writer.newLine();
                }
                JOptionPane.showMessageDialog(null, "Books saved successfully!");
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error saving books: " + e.getMessage());
            }
        }
    }

    // Save Persons to CSV (user chooses any file)
    public static void savePersons(ObservableList<Person> persons) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("CSV files", "csv"));
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (!file.getName().toLowerCase().endsWith(".csv")) {
                file = new File(file.getAbsolutePath() + ".csv");
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write("Name,Email");
                writer.newLine();
                for (Person p : persons) {
                    writer.write(p.getName() + "," + p.getEmail());
                    writer.newLine();
                }
                JOptionPane.showMessageDialog(null, "Persons saved successfully!");
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error saving persons: " + e.getMessage());
            }
        }
    }

    // Load Books from user-selected CSV
    public static ObservableList<Book> loadBooks() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Books CSV File");
        fileChooser.setFileFilter(new FileNameExtensionFilter("CSV files", "csv"));

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            ObservableList<Book> books = FXCollections.observableArrayList();

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                reader.readLine(); // skip header
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    String title = parts[0];
                    String author = parts[1];
                    boolean available = parts.length > 2 && Boolean.parseBoolean(parts[2]);
                    books.add(new Book(title, author));
                }
                return books;
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error loading books: " + e.getMessage());
            }
        }
        return FXCollections.observableArrayList();
    }

    // Load Persons from user-selected CSV
    public static ObservableList<Person> loadPersons() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Persons CSV File");
        fileChooser.setFileFilter(new FileNameExtensionFilter("CSV files", "csv"));

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            ObservableList<Person> persons = FXCollections.observableArrayList();

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                reader.readLine(); // skip header
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    String name = parts[0];
                    String email = parts.length > 1 ? parts[1] : "";
                    persons.add(new Person(name, email));
                }
                return persons;
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error loading persons: " + e.getMessage());
            }
        }
        return FXCollections.observableArrayList();
    }
}
