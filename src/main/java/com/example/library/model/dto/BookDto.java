package com.example.library.model.dto;

import com.example.library.model.Author;
import com.example.library.model.enumeration.Category;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Data
public class BookDto {


    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    private Long authorId;

    private Integer availableCopies;

    public BookDto(String name, Category category, Long authorId, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.authorId = authorId;
        this.availableCopies = availableCopies;
    }
}
