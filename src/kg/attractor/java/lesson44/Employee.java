package kg.attractor.java.lesson44;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String id;
    private String name;
    private String email;
    private String password;
    private List<String> borrowedBooks;

    public Employee(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
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

    public void borrowBook(String bookId) {
        if (borrowedBooks == null) {
            borrowedBooks = new ArrayList<>();
        }
        borrowedBooks.add(bookId);
    }
}