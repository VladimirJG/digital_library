package crud.spring.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Book {
    private int bookId;
    @NotEmpty(message = "Нименование не может быть пустым")
    @Size(min = 2, max = 200, message = "Размер наименования книги должен находиться в диапазоне от 2 до 200")
    private String name;
    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 2, max = 100, message = "Размер имени должен находиться в диапазоне от 2 до 100 символов(включая пробелы)")
    private String author;
    @Pattern(regexp = "\\d{4}", message = "Размер даты = 4 символам")
    private int yearOfIssue;

    public Book() {
    }

    public Book( String name, String author, int yearOfIssue) {
        this.name = name;
        this.author = author;
        this.yearOfIssue = yearOfIssue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfIssue() {
        return yearOfIssue;
    }

    public void setYearOfIssue(int yearOfIssue) {
        this.yearOfIssue = yearOfIssue;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
