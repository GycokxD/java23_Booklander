package kg.attractor.java.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kg.attractor.java.lesson44.Book;
import kg.attractor.java.lesson44.BookLoan;
import kg.attractor.java.lesson44.Employee;
import kg.attractor.java.lesson44.Genre;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class DataSaver {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void saveBooks(String filePath, List<Book> books) throws DataException {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(Map.of("books", books), writer);
        } catch (IOException e) {
            throw new DataException("Ошибка при сохранении книг в файл: " + filePath, e);
        }
    }

    public static void saveEmployees(String filePath, List<Employee> employees) throws DataException {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(Map.of("employees", employees), writer);
        } catch (IOException e) {
            throw new DataException("Ошибка при сохранении сотрудников в файл: " + filePath, e);
        }
    }

    public static void saveBookLoans(String filePath, List<BookLoan> bookLoans) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(Map.of("loans", bookLoans), writer);
        }
    }

    public static void saveGenres(String filePath, List<Genre> genres) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(Map.of("genres", genres), writer);
        }
    }
}