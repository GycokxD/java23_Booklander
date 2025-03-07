package kg.attractor.java.lesson44;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;

public class Employee {
    @Expose
    private String id;
    @Expose
    private String name;
    @Expose
    private String email;
    @Expose
    private String password;
    @Expose
    private List<String> borrowedBooks;
    @Expose
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

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<String> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public List<String> getBorrowedBooksHistory() {
        return borrowedBooksHistory;
    }

    public void setBorrowedBooksHistory(List<String> borrowedBooksHistory) {
        this.borrowedBooksHistory = borrowedBooksHistory;
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