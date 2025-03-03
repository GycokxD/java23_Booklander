package kg.attractor.java.lesson44;

public class Book {
    private String id;
    private String title;
    private String author;
    private String genre;
    private boolean borrowed;
    private String image;

    public Book(String id, String title, String author, String genre, boolean borrowed, String image) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.borrowed = borrowed;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }
}