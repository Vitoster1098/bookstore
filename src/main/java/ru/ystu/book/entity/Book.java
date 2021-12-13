package ru.ystu.book.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private String year;
    private double price;
    private String image;

    public Book(){}

    public Book(Long id, String name, String description, String year, double price, String image){
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.year = year;
        this.price = price;
        this.image = image;
    }

    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}

    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    public String getDescription(){return description;}
    public void setDescription(String description){this.description = description;}

    public String getYear(){return year;}
    public void setYear(String year){this.year = year;}

    public double getPrice(){return price;}
    public void setPrice(double price){this.price = price;}

    public String getImage(){return image;}
    public void setImage(String image){this.image = image;}
}




