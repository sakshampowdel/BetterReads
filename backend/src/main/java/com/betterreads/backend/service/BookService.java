package com.betterreads.backend.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.betterreads.backend.repository.AuthorRepository;
import com.betterreads.backend.repository.BookRepository;
import com.betterreads.backend.dto.AuthorResponseDto;
import com.betterreads.backend.dto.BookRequestDto;
import com.betterreads.backend.dto.BookResponseDto;
import com.betterreads.backend.dto.PaginatedResponseDto;
import com.betterreads.backend.exception.BookNotFoundException;
import com.betterreads.backend.model.Author;
import com.betterreads.backend.model.Book;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(final BookRepository bookRepository,  final AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public BookResponseDto getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            throw new BookNotFoundException("Book with id " + id + " doesn't exist!");
        }

        return mapToResponseDto(book.get());
    }

    public PaginatedResponseDto<BookResponseDto> getAllBooks(Pageable pageable) {
        Page<Book> page = bookRepository.findAll(pageable);
        List<Book> books = page.getContent();
        List<BookResponseDto> bookResponseDtos = new ArrayList<>();

        for (Book book: books) {
            bookResponseDtos.add(mapToResponseDto(book));
        }

        PaginatedResponseDto<BookResponseDto> paginatedResponseDto = new PaginatedResponseDto<>(bookResponseDtos, page.getNumber(), page.getSize(), page.getTotalPages(), page.getTotalElements());
        
        return paginatedResponseDto;
    }

    public BookResponseDto createBook(BookRequestDto bookRequestDto) {
        String title = bookRequestDto.getTitle();
        Set<Author> author = new HashSet<>(authorRepository.findAllById(bookRequestDto.getAuthorIds()));
        String isbn = bookRequestDto.getIsbn();

        Optional<Book> duplicateBook = bookRepository.findByIsbn(isbn);
        if (duplicateBook.isPresent()) {
            return mapToResponseDto(duplicateBook.get());
        }

        Book book = new Book(title, author, isbn);

        bookRepository.save(book);

        return mapToResponseDto(book);
    }

    public BookResponseDto updateBookById(Long id, BookRequestDto bookRequestDto) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            throw new BookNotFoundException("Book with id " + id + " doesn't exist!");
        }

        String title = bookRequestDto.getTitle();
        Set<Author> author = new HashSet<>(authorRepository.findAllById(bookRequestDto.getAuthorIds()));
        String isbn = bookRequestDto.getIsbn();

        book.get().setTitle(title);
        book.get().setAuthor(author);
        book.get().setIsbn(isbn);

        bookRepository.save(book.get());

        return mapToResponseDto(book.get());
    }

    public void deleteBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            throw new BookNotFoundException("Book with id " + id + " doesn't exist!");
        }

        bookRepository.deleteById(id);
    }

    public BookResponseDto mapToResponseDto(Book book) {
        Set<Author> authors = book.getAuthor();
        Set<AuthorResponseDto> authorResponseDtos = new HashSet<>();
        for (Author author: authors) {
            authorResponseDtos.add(new AuthorResponseDto(author.getId(), author.getName()));
        }

        return new BookResponseDto(book.getId(), book.getTitle(), authorResponseDtos, book.getIsbn());
    }
}
