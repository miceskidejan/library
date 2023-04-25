package com.example.library.model;

import com.example.library.model.enumeration.Category;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    private Author author;

    private Integer availableCopies;

    private boolean borrowed=false;

    public Book(){

    }

    public Book(String name, Category category, Author author, Integer availableCopies){
        this.name=name;
        this.category=category;
        this.author=author;
        this.availableCopies=availableCopies;
    }

}
