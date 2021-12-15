package ru.ystu.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ystu.book.entity.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findAllByNameContains(String name);
    Optional<Book> findAllById(Long id);
    long countAllByName(String name);
}
