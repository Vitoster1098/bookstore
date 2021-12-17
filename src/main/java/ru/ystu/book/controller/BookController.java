package ru.ystu.book.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ystu.book.entity.Book;
import ru.ystu.book.entity.Cart;
import ru.ystu.book.entity.Role;
import ru.ystu.book.entity.User;
import ru.ystu.book.repository.BookRepository;
import ru.ystu.book.repository.CartRepository;
import ru.ystu.book.repository.UserRepository;

import java.io.FileNotFoundException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@Log4j2
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookRepository bookRep;

    @Autowired
    private CartRepository cartRep;

    @Autowired
    private UserRepository userRep;

    @ResponseStatus(
            value = HttpStatus.NOT_FOUND,
            reason = "Not found")
    @ExceptionHandler(FileNotFoundException.class)
    public void fileNotFoundHandler(){}

    @GetMapping("/")
    public String getAllBooks(Model model, Principal user) {
        User curUser = userRep.findByUsername(user.getName());
        Set<Role> role = curUser.getRoles();
        Iterable<Book> items = bookRep.findAll();
        model.addAttribute("books", items);
        if(role.toArray()[0] == Role.USER){
            model.addAttribute("user_role", "USER");
        } else {
            model.addAttribute("user_role", "ADMIN");
        }
        return "books";
    }
    @GetMapping("/add")
    public String getAddBooks(Model model){
        return "book_add";
    }
    @PostMapping("/add")
    public String addBooks(Book book, Model model){
        bookRep.save(book);
        return "redirect:/";
    }
    @GetMapping("/{id}")
    public String getBook(@PathVariable Long id, Model model, Principal user) throws FileNotFoundException {
        Optional<Book> optionalBook = bookRep.findAllById(id);
        if (optionalBook.isEmpty())
            throw new FileNotFoundException();
        User curUser = userRep.findByUsername(user.getName());
        Set<Role> role = curUser.getRoles();
        if(role.toArray()[0] == Role.USER){
            model.addAttribute("user_role", "USER");
        } else {
            model.addAttribute("user_role", "ADMIN");
        }
        model.addAttribute("book", optionalBook.get());

        return "book";
    }
    @PostMapping("/{id}")
    public String removeBookById(@PathVariable Long id) {
        bookRep.deleteById(id);
        return "redirect:/";
    }
    @GetMapping("/byname/{name}")
    public String getBookByName(@PathVariable String name, Model model) throws FileNotFoundException {
        Optional<Book> optionalBook = bookRep.findAllByNameContains(name);
        if (optionalBook.isEmpty())
            throw new FileNotFoundException();
        model.addAttribute("book", optionalBook.get());
        return "book";
    }
    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, Model model, Principal user) throws FileNotFoundException {
        Optional<Book> optionalBook = bookRep.findAllById(id);
        if (optionalBook.isEmpty())
            throw new FileNotFoundException();
        User curUser = userRep.findByUsername(user.getName());
        Set<Role> role = curUser.getRoles();
        if(role.toArray()[0] == Role.USER){
            model.addAttribute("user_role", "USER");
        } else {
            model.addAttribute("user_role", "ADMIN");
        }
        model.addAttribute("book", optionalBook.get());

        return "book_edit";
    }
    @GetMapping("/remove/{id}")
    public String removeBook(Model model, @PathVariable Long id) throws FileNotFoundException {
        Optional<Book> optionalBook = bookRep.findAllById(id);
        if (optionalBook.isEmpty())
            throw new FileNotFoundException();
        bookRep.deleteById(id);
        return "redirect:/";
    }
    @GetMapping("/cart/{id}")
    public String addToCart(@PathVariable Long id, Principal user){
        Cart cart = new Cart(userRep.findByUsername(user.getName()).getId(), id);
        cartRep.save(cart);
        return "redirect:/";
    }
    @GetMapping("/mycart")
    public String myCart(Model model, Principal user) {
        User curUser = userRep.findByUsername(user.getName());
        Set<Role> role = curUser.getRoles();
        if(role.toArray()[0] == Role.USER){
            model.addAttribute("user_role", "USER");
        } else {
            model.addAttribute("user_role", "ADMIN");
        }
        List<Cart> carts = cartRep.findAllByUser(userRep.findByUsername(user.getName()).getId());
        List<Book> books = new ArrayList<>();
        double curPrice = 0;
        for(Cart cart : carts) {
            Optional<Book> bk = bookRep.findById(cart.getBookId());
            bk.ifPresent(books::add);
            curPrice += bk.get().getPrice();
        }
        model.addAttribute("cartitems", books);
        model.addAttribute("totalprice", curPrice);
        return "mycart";
    }
    @GetMapping("/mycart/remove/{id}")
    public String remFromCart(@PathVariable Long id, Model model, Principal user){
        List<Cart> carts = cartRep.findAllByUser(userRep.findByUsername(user.getName()).getId());
        for (Cart cart : carts) {
            Optional<Book> bk = bookRep.findById(cart.getBookId());
            if(bk.get().getId() == id){
                cartRep.delete(cart);
                break;
            }
        }
        return "redirect:/books/mycart";
    }
}
