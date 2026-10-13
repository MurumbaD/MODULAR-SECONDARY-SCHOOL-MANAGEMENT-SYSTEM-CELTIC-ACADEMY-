package ug.ac.vu.g01.library;

import java.io.*;
import java.util.*;

/**
 * Service managing library book inventory and checkouts.
 * @author Tibarimbasa Isaac (Group 01)
 */
public class LibraryService {
    private final List<LibraryBook> books = new ArrayList<>();
    private final String storageFile = "G01_Celtec_LibraryData.txt";

    public LibraryService() {
        loadFromFile();
    }

    public void addBook(LibraryBook book) {
        books.add(book);
        saveToFile();
    }

    public List<LibraryBook> getAllBooksSortedByTitle() {
        List<LibraryBook> sorted = new ArrayList<>(books);
        sorted.sort(Comparator.comparing(LibraryBook::getTitle));
        return sorted;
    }

    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(storageFile))) {
            for (LibraryBook b : books) {
                writer.println(b.getId() + ";" + b.getTitle() + ";" + b.getAuthor() + ";" + b.getAvailableCopies());
            }
        } catch (IOException e) {
            System.err.println("Warning: Could not save library data: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        File file = new File(storageFile);
        if (!file.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 4) {
                    books.add(new LibraryBook(parts[0], parts[1], parts[2], Integer.parseInt(parts[3])));
                }
            }
        } catch (Exception e) {
            System.out.println("Note: Initialized library catalog.");
        }
    }
}