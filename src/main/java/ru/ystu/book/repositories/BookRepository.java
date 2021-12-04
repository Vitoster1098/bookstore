package ru.ystu.book.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ystu.book.domain.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByNameContains(String name);
}
