package com.betterreads.backend.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.betterreads.backend.repository.BookRepository;
import com.betterreads.backend.dto.BookRequestDto;
import com.betterreads.backend.exception.BookNotFoundException;
import com.betterreads.backend.model.Book;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            throw new BookNotFoundException("Book with id " + id + " doesn't exist!");
        }

        return book.get();
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book createBook(BookRequestDto bookRequestDto) {
        String title = bookRequestDto.getTitle();
        String author = bookRequestDto.getAuthor();
        String isbn = bookRequestDto.getIsbn();

        Book book = new Book(title, author, isbn);

        bookRepository.save(book);

        return book;
    }
}
