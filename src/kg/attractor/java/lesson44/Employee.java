package kg.attractor.java.lesson44;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String id;
    private String name;
    private String email;
    private String password;
    private List<String> borrowedBooks;
    private List<String> borrowedBooksHistory;

    public Employee(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.borrowedBooks = new ArrayList<>();
        this.borrowedBooksHistory = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getBorrowedBooks() {
        return borrowedBooks;
    }

    public List<String> getBorrowedBooksHistory() {
        return borrowedBooksHistory;
    }

    public void borrowBook(String bookId) {
        if (!borrowedBooks.contains(bookId)) {
            borrowedBooks.add(bookId);
            borrowedBooksHistory.add(bookId);
        }
    }

    public void returnBook(String bookId) {
        borrowedBooks.remove(bookId);
    }
}