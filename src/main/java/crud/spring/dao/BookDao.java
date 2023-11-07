package crud.spring.dao;

import crud.spring.models.Book;
import crud.spring.models.PersonReader;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDao {
    private final JdbcTemplate jdbcTemplate;

    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> showAllBooks() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book showBook(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }


    public void saveBook(Book book) {
        jdbcTemplate.update("INSERT INTO Book(book_name, author, year_of_production) VALUES (?,?,?)",
                book.getName(), book.getAuthor(), book.getYearOfIssue());
    }

    public void updateBook(Book upBook, int id) {
        jdbcTemplate.update("UPDATE Book SET book_name=?, author=?,year_of_production=? WHERE book_id=?",
                upBook.getName(), upBook.getAuthor(), upBook.getYearOfIssue(), id);
    }

    public void deleteBook(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", id);
    }

    public Optional<PersonReader> getBookOwner(int id) {
        return jdbcTemplate.query("SELECT Person_Reader.* FROM Book JOIN person_reader ON book.reader_id = person_reader.reader_id WHERE book.book_id=?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(PersonReader.class)).stream().findAny();
    }

    public void release(int id) {
        jdbcTemplate.update("UPDATE book SET reader_id=NULL WHERE book_id=?", id); //id= bookid
    }

    public void assign (int id, PersonReader selectedReader){
        jdbcTemplate.update("UPDATE book SET reader_id=? WHERE book_id=?",selectedReader.getReaderId(),id); //id= bookid
    }
}

