package kg.attractor.java.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kg.attractor.java.lesson44.Book;
import kg.attractor.java.lesson44.BookLoan;
import kg.attractor.java.lesson44.Employee;
import kg.attractor.java.lesson44.Genre;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class DataLoader {
    private static final Gson gson = new Gson();

    public static List<Book> loadBooks(String filePath) throws Exception {
        try (FileReader reader = new FileReader(filePath)) {
            Type type = new TypeToken<Map<String, List<Book>>>() {}.getType();
            Map<String, List<Book>> data = gson.fromJson(reader, type);
            return data.get("books");
        }
    }

    public static List<Employee> loadEmployees(String filePath) throws Exception {
        try (FileReader reader = new FileReader(filePath)) {
            Type type = new TypeToken<Map<String, List<Employee>>>() {}.getType();
            Map<String, List<Employee>> data = gson.fromJson(reader, type);
            return data.get("employees");
        }
    }

    public static List<BookLoan> loadBookLoans(String filePath) throws Exception {
        try (FileReader reader = new FileReader(filePath)) {
            Type type = new TypeToken<Map<String, List<BookLoan>>>() {}.getType();
            Map<String, List<BookLoan>> data = gson.fromJson(reader, type);
            return data.get("loans");
        }
    }

    public static List<Genre> loadGenres(String filePath) throws Exception {
        try (FileReader reader = new FileReader(filePath)) {
            Type type = new TypeToken<Map<String, List<Genre>>>() {}.getType();
            Map<String, List<Genre>> data = gson.fromJson(reader, type);
            return data.get("genres");
        }
    }
}