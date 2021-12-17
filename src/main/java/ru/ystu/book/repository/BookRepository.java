package ru.ystu.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.ystu.book.entity.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findAllByNameContains(String name);
    Optional<Book> findAllByYear(String year);
    Optional<Book> PriceIsBetween(double min, double max);
    Optional<Book> findAllById(Long id);
    long countAllByName(String name);

    @Query("select new Book(u.id, u.name, u.description, u.year,u.price) from Book u where u.name like %:name% and u.year=:year and u.price>:minprice and u.price<:maxprice")
    List<Book> findWithLike(@Param("name") String name, @Param("year") String year, @Param("minprice") double minprice,
                            @Param("maxprice") double maxprice);

    @Query("select new Book(u.id, u.name, u.description, u.year,u.price) from Book u where u.year=:year and u.price>:minprice and u.price<:maxprice")
    List<Book> findWithLike2(@Param("year") String year, @Param("minprice") double minprice,
                            @Param("maxprice") double maxprice);

    @Query("select new Book(u.id, u.name, u.description, u.year,u.price) from Book u where u.name like %:name% and u.price>:minprice and u.price<:maxprice")
    List<Book> findWithLike3(@Param("name") String name, @Param("minprice") double minprice,
                                 @Param("maxprice") double maxprice);

    @Query("select new Book(u.id, u.name, u.description, u.year,u.price) from Book u where u.price>:minprice and u.price<:maxprice")
    List<Book> findWithLike4(@Param("minprice") double minprice,
                                 @Param("maxprice") double maxprice);
}

