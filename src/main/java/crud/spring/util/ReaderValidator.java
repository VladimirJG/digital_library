package crud.spring.util;

import crud.spring.dao.BookDao;
import crud.spring.dao.PersonReaderDao;
import crud.spring.models.Book;
import crud.spring.models.PersonReader;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ReaderValidator implements Validator {
    private final PersonReaderDao personReaderDao;

    public ReaderValidator(PersonReaderDao personReaderDao) {
        this.personReaderDao = personReaderDao;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return PersonReader.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
       PersonReader reader = (PersonReader) target;
        if (personReaderDao.getReaderByName(reader.getName()).isPresent()) {
            errors.rejectValue("name", "", "Такой человек уже существует");
        }
    }
}
