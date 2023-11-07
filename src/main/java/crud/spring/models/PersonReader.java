package crud.spring.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class PersonReader {
    private int readerId;
    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 2, max = 100, message = "Размер имени должен находиться в диапазоне от 2 до 100 символов(включая пробелы)")
    private String name;
    @Min(value = 1900, message = "Значение не должно быть менее 1900")
    private int yearOfBirth;

    public PersonReader() {
    }

    public PersonReader(String name, int yearOfBirth) {
        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }
}
