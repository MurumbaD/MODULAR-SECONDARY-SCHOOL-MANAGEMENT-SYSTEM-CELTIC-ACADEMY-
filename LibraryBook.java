package ug.ac.vu.g01.library;

import ug.ac.vu.g01.core.Entity;

/**
 * Represents secondary school textbook inventory.
 * @author Tibarimbasa Isaac (Group 01)
 */
public class LibraryBook extends Entity {
    private String title;
    private String author;
    private int availableCopies;

    public LibraryBook(String id, String title, String author, int availableCopies) throws LoanLimitExceededException {
        super(id);
        setTitle(title);
        setAuthor(author);
        setAvailableCopies(availableCopies);
    }

    public String getTitle() { return title; }
    public void setTitle(String title) throws LoanLimitExceededException {
        if (title == null || title.isBlank()) throw new LoanLimitExceededException("Book title cannot be blank.");
        this.title = title.trim();
    }

    public String getAuthor() { return author; }
    public void setAuthor(String author) throws LoanLimitExceededException {
        if (author == null || author.isBlank()) throw new LoanLimitExceededException("Author cannot be blank.");
        this.author = author.trim();
    }

    public int getAvailableCopies() { return availableCopies; }
    public void setAvailableCopies(int availableCopies) throws LoanLimitExceededException {
        if (availableCopies < 0) throw new LoanLimitExceededException("Available copies cannot be negative.");
        this.availableCopies = availableCopies;
    }

    @Override
    public String getDetails() {
        return String.format("Book ID: %-10s | Title: %-22s | Author: %-15s | Copies: %d",
                id, title, author, availableCopies);
    }
}