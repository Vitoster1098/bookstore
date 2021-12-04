package ru.ystu.book.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import ru.ystu.book.domain.Book;
import ru.ystu.book.repositories.BookRepository;

import javax.annotation.PostConstruct;

@Configuration
@RequiredArgsConstructor
public class BookConfiguration {
    private final BookRepository repository;

    @PostConstruct
    public void init(){
        repository.save(new Book(1L, "Книга1", "2000"));
        repository.save(new Book(2L, "Книга2", "2001"));
        repository.save(new Book(3L, "Книга3", "2002"));
        repository.save(new Book(4L, "Книга4", "2003"));
        repository.save(new Book(5L, "Книга5", "2004"));
        repository.save(new Book(6L, "Книга6", "2005"));
        repository.save(new Book(7L, "Книга7", "2006"));
        repository.save(new Book(8L, "Книга8", "2007"));

    }
}
