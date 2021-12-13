package ru.ystu.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ystu.book.entity.Cart;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByUser(Long user);
}
