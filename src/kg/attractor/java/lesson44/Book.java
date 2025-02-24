package kg.attractor.java.lesson44;

public class Book {
    private String id;
    private String title;
    private String author;
    private String genre;
    private boolean borrowed;

    public Book(String id, String title, String author, String genre, boolean borrowed) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.borrowed = borrowed;
    }

    public boolean getBorrowed() {
        return borrowed;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }
}