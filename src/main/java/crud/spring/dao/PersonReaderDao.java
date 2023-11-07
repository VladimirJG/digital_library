package crud.spring.dao;

import crud.spring.models.Book;
import crud.spring.models.PersonReader;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonReaderDao {
    private final JdbcTemplate jdbcTemplate;

    public PersonReaderDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<PersonReader> showAllReaders() {
        return jdbcTemplate.query("SELECT * FROM Person_Reader", new BeanPropertyRowMapper<>(PersonReader.class));
    }

    public PersonReader showReader(int readerId) {
        return jdbcTemplate.query("SELECT * FROM Person_Reader WHERE reader_id=?", new Object[]{readerId}, new BeanPropertyRowMapper<>(PersonReader.class))
                .stream().findAny().orElse(null);
    }

    public Optional<PersonReader> getReaderByName(String name) {
        return jdbcTemplate.query("SELECT * FROM Person_Reader WHERE name=?", new Object[]{name}, new BeanPropertyRowMapper<>(PersonReader.class))
                .stream().findAny();
    }

    public void saveReader(PersonReader reader) {
        jdbcTemplate.update("INSERT INTO Person_Reader(name,year_of_birthday) VALUES (?,?)",
                reader.getName(), reader.getYearOfBirth());
    }

    public void updateReader(PersonReader upReader, int id) {
        jdbcTemplate.update("UPDATE Person_Reader SET name=?, year_of_birthday=? WHERE reader_id=?", upReader.getName(),
                upReader.getYearOfBirth(), id);
    }

    public void deleteReader(int id) {
        jdbcTemplate.update("DELETE FROM Person_reader WHERE reader_id=?", id);
    }

    public List<Book> getBooksByReaderId(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE book_reader_id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class));
    }
}
