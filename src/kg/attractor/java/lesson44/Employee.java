package kg.attractor.java.lesson44;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String id;
    private String name;
    private String email;
    private List<Book> borrowedBooks = new ArrayList<>();

    public Employee(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
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

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
        book.setBorrowed(true);
    }
}