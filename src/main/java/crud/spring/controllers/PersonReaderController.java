package crud.spring.controllers;

import crud.spring.dao.PersonReaderDao;
import crud.spring.models.PersonReader;
import crud.spring.util.ReaderValidator;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/readers")
public class PersonReaderController {
    private final PersonReaderDao personReaderDao;
    private final ReaderValidator readerValidator;

    public PersonReaderController(PersonReaderDao personReaderDao, ReaderValidator readerValidator) {
        this.personReaderDao = personReaderDao;
        this.readerValidator = readerValidator;
    }

    @GetMapping
    public String showAllReaders(Model model) {
        model.addAttribute("readers", personReaderDao.showAllReaders());
        return "readers/allReaders";
    }

    @GetMapping("/{id}")
    public String showReader(@PathVariable("id") int id, Model model) {
        model.addAttribute("reader", personReaderDao.showReader(id));
        model.addAttribute("books", personReaderDao.getBooksByReaderId(id));
        return "readers/reader";
    }

    @GetMapping("/new")
    public String newReader(@ModelAttribute("reader") PersonReader reader) {
        return "readers/new";
    }

    @PostMapping
    public String createReader(@ModelAttribute("reader") @Valid PersonReader reader, BindingResult bindingResult) {
        readerValidator.validate(reader, bindingResult);
        if (bindingResult.hasErrors()) {
            return "readers/new";
        }
        personReaderDao.saveReader(reader);
        return "redirect:/readers";
    }

    @GetMapping("/{id}/edit")
    public String editReader(Model model, @PathVariable("id") int id) {
        model.addAttribute("reader", personReaderDao.showReader(id));
        return "readers/edit";
    }

    @PatchMapping("/{id}")
    public String updateReader(@ModelAttribute("reader") @Valid PersonReader reader, BindingResult bindingResult,
                               @PathVariable("id") int id) {
        readerValidator.validate(reader, bindingResult);
        if (bindingResult.hasErrors()) {
            return "readers/edit";
        }
        personReaderDao.updateReader(reader, id);
        return "redirect:/readers";
    }

    @DeleteMapping("/{id}")
    public String deleteReader(@PathVariable("id") int id) {
        personReaderDao.deleteReader(id);
        return "redirect:/readers";
    }
}
