package ru.ystu.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ystu.book.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    Optional<User> findById(Long id);
}
