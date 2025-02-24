package kg.attractor.java.lesson44;

public class BookLoan {
    private String bookId;
    private String employeeId;

    public BookLoan(String bookId, String employeeId) {
        this.bookId = bookId;
        this.employeeId = employeeId;
    }

    public String getBookId() {
        return bookId;
    }

    public String getEmployeeId() {
        return employeeId;
    }
}