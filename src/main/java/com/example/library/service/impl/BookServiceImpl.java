package com.example.library.service.impl;

import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.model.dto.BookDto;
import com.example.library.model.enumeration.Category;
import com.example.library.model.exception.AuthorNotFoundException;
import com.example.library.model.exception.BookNotFoundException;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import com.example.library.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> save(String name, Long authorId, Category category, Integer availableCopies) {
        Author author=authorRepository.findById(authorId)
                .orElseThrow(()-> new AuthorNotFoundException(authorId));

        this.bookRepository.deleteByName(name);
        return Optional.of(this.bookRepository.save(new Book(name,category,author,availableCopies)));
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Author author=authorRepository.findById(bookDto.getAuthorId())
                .orElseThrow(()-> new AuthorNotFoundException(bookDto.getAuthorId()));

        this.bookRepository.deleteByName(bookDto.getName());
        return Optional.of(this.bookRepository.save(new Book(bookDto.getName(),bookDto.getCategory(),author,bookDto.getAvailableCopies())));
    }

    @Override
    public Optional<Book> edit(Long id, String name, Long authorId, Long categoryId, Integer availableCopies) {
        return Optional.empty();
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book = this.bookRepository.findById(id).orElseThrow(()-> new BookNotFoundException(id));
        Author author=authorRepository.findById(bookDto.getAuthorId())
                .orElseThrow(()-> new AuthorNotFoundException(bookDto.getAuthorId()));
        book.setName(bookDto.getName());
        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());
        book.setCategory(bookDto.getCategory());

        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public void delete(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> borrow(Long id) {
        Book book = this.bookRepository.findById(id).orElseThrow(()-> new BookNotFoundException(id));
        book.setBorrowed(true);
        book.setAvailableCopies(book.getAvailableCopies()-1);
        return Optional.of(this.bookRepository.save(book));
    }
}
