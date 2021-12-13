package ru.ystu.book.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
    private Long user;
    private Long bookId;

    public Cart(){}

    public Cart(Long user, Long bookId){
        this.user = user;
        this.bookId = bookId;
    }

    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}

    public Long getUser(){return user;}
    public void setUser(Long user){this.user = user;}

    public Long getBookId(){return bookId;}
    public void setBookId(Long bookId){this.bookId = bookId;}
}