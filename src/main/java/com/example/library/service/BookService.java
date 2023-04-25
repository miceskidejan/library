package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.model.dto.BookDto;
import com.example.library.model.enumeration.Category;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();
    Optional<Book> findById(Long id);
    Optional<Book> save(String name, Long authorId, Category category, Integer availableCopies);
    Optional<Book> save(BookDto bookDto);
    Optional<Book> edit(Long id, String name, Long authorId, Long categoryId, Integer availableCopies);
    Optional<Book> edit(Long id, BookDto bookDto);
    void delete(Long id);
    Optional<Book> borrow(Long id);
}
