package ru.ystu.book.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Cart")
public class Cart {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column
    private Long id;
    @JoinColumn(name = "bookid")
    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;
    @Column
    private byte count_book;
    @JoinColumn(name = "userid")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}