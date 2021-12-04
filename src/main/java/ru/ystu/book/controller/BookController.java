package ru.ystu.book.controller;

import com.sun.source.tree.OpensTree;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.dom4j.rule.Mode;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ystu.book.domain.Book;
import ru.ystu.book.repositories.BookRepository;

import java.io.FileNotFoundException;
import java.util.Optional;

@Controller
@Log4j2
@RequiredArgsConstructor
public class BookController {
    private final BookRepository repository;

    @ResponseStatus(
            value = HttpStatus.NOT_FOUND,
            reason = "Npt found")
    @ExceptionHandler(FileNotFoundException.class)
    public void fileNotFoundHandler(){}

    @GetMapping("/book")
    public String getBook(Model model){
        model.addAttribute("books", repository.findAll());
        return "books";
    }

    @PostMapping("/book")
    public String addBook(Book book){
        repository.save(book);
        return "redirect:/book";
    }

    @GetMapping("/book/{id}")
    public String getBook(@PathVariable Long id, Model model) throws FileNotFoundException {
        Optional<Book> optionalBook = repository.findById(id);
        if(optionalBook.isEmpty())
            throw new FileNotFoundException();
        model.addAttribute("book", optionalBook.get());
        return "book";
    }

    @GetMapping("/book/byname/{name}")
    public String getBookByName(@PathVariable String name, Model model){
        model.addAttribute("books", repository.findAllByNameContains(name));
        return "books";
    }

    @GetMapping("/book/delete/{id}")
    public String delete(@PathVariable Long id){
        repository.deleteById(id);
        return "redirect:/book";
    }
}
