package crud.spring.util;

import crud.spring.dao.BookDao;
import crud.spring.models.Book;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BookValidator implements Validator {
    private final BookDao bookDao;

    public BookValidator(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;
        if (bookDao.showBook(book.getName()).isPresent()) {
            errors.rejectValue("name", "", "Книга с таким наименованием уже существует");
        }
    }
}
