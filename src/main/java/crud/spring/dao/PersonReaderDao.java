package crud.spring.dao;

import crud.spring.models.PersonReader;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class PersonReaderDao {
    private JdbcTemplate jdbcTemplate;

    public PersonReaderDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<PersonReader> showAllReaders() {
        return jdbcTemplate.query("SELECT * FROM Person_Reader", new BeanPropertyRowMapper<>(PersonReader.class));
    }

    public Optional<PersonReader> showReader(int id) {
        return jdbcTemplate.query("SELECT * FROM Person_Reader WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(PersonReader.class))
                .stream().findAny();
    }

    public void saveReader(PersonReader reader) {
        jdbcTemplate.update("INSERT INTO Person_Reader(name,year_of_birthday) VALUES (?,?)",
                reader.getName(), reader.getYearOfBirth());
    }

    public void updateReader(PersonReader upReader, int id) {
        jdbcTemplate.update("UPDATE Person_Reader SET name=?, year_of_birthday=? WHERE id=?", upReader.getName(),
                upReader.getYearOfBirth(), id);
    }

    public void deleteReader(int id) {
        jdbcTemplate.update("DELETE FROM Person_reader WHERE id=?", id);
    }

}
