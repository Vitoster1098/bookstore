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
    Optional<Book> findAllById(Long id);
    long countAllByName(String name);

    @Query("select id, name, price, year from Book where name like :name and year=:year and price>:minprice and price<:maxprice")
    List<Book> findWithLike(@Param("name") String name, @Param("year") String year, @Param("minprice") double minprice,
                          @Param("maxprice") double maxprice);

    @Query("select id, name, price, year from Book where year=:year and price>:minprice and price<:maxprice")
    List<Book> findWithLike2(@Param("year") String year, @Param("minprice") double minprice,
                            @Param("maxprice") double maxprice);

    @Query("select id, name, price, year from Book where price>:minprice and price<:maxprice")
    List<Book> findWithLike3(@Param("minprice") double minprice,
                             @Param("maxprice") double maxprice);

    @Query("select id, name, price, year from Book where price<:maxprice")
    List<Book> findWithLike4(@Param("maxprice") double maxprice);

    @Query("select id, name, price, year from Book where price>:minprice")
    List<Book> findWithLike5(@Param("minprice") double minprice);

    @Query("select id, name, price, year from Book where year=:year")
    List<Book> findWithLike6(@Param("year") String year);

    @Query("select id, name, price, year from Book where year like :name")
    List<Book> findWithLike7(@Param("name") String name);
}

