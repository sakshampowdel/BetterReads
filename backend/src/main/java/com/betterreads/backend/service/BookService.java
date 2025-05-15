package com.betterreads.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betterreads.backend.repository.BookRepository;
import com.betterreads.backend.model.Book;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book getBookById(Long id) {
        if (!bookRepository.existsById(id)) {
            return null;
        }

        return bookRepository.getReferenceById(id);
    }
}
