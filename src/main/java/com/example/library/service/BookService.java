package com.example.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        return optionalBook.orElse(null);
    }

    public Book addBook(Book book) {
        // Perform any necessary validation or business logic before saving the book
        // For example, you could check if the book already exists, validate input fields, etc.
        
        // Save the book using the BookRepository
        return bookRepository.save(book);
    }

    public Book updateBook(Book updatedBook) {
        // Perform any necessary validation or business logic before updating the book
        
        // Save the updated book using the BookRepository
        return bookRepository.save(updatedBook);
    }
}
