package ru.ystu.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ystu.book.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
