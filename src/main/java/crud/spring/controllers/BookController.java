package crud.spring.controllers;

import crud.spring.dao.BookDao;
import crud.spring.dao.PersonReaderDao;
import crud.spring.models.Book;
import crud.spring.models.PersonReader;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDao bookDao;
    private final PersonReaderDao personReaderDao;


    public BookController(BookDao bookDao, PersonReaderDao personReaderDao) {
        this.bookDao = bookDao;
        this.personReaderDao = personReaderDao;
    }

    @GetMapping
    public String showAllBooks(Model model) {
        model.addAttribute("books", bookDao.showAllBooks());
        return "books/allBooks";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model,
                           @ModelAttribute("reader") PersonReader reader) {
        model.addAttribute("book", bookDao.showBook(id));
        Optional<PersonReader> bookOwner = bookDao.getBookOwner(id);

        if (bookOwner.isPresent()) {
            model.addAttribute("owner", bookOwner.get());
        } else {
            model.addAttribute("reader", personReaderDao.showAllReaders());
        }
        return "books/book";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/newR";
    }

    @PostMapping
    public String createBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/newR";
        }
        bookDao.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBook(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDao.showBook(id));
        return "books/editR";
    }

    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                             @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "books/editR";
        }
        bookDao.updateBook(book, id);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookDao.deleteBook(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        bookDao.release(id);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("reader") PersonReader selectedReader) {
        bookDao.assign(id, selectedReader);
        return "redirect:/books/" + id;
    }
}
