package crud.spring.dao;

import crud.spring.models.PersonReader;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class PersonReaderDao {
    private JdbcTemplate jdbcTemplate;

    public PersonReaderDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<PersonReader> showAll(){
//        jdbcTemplate.query()
return null;
    }
}
