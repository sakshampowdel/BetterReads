package com.betterreads.backend.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.betterreads.backend.dto.AuthorResponseDto;
import com.betterreads.backend.dto.BookRequestDto;
import com.betterreads.backend.dto.BookResponseDto;
import com.betterreads.backend.dto.PaginatedResponseDto;
import com.betterreads.backend.exception.AuthorNotFoundException;
import com.betterreads.backend.exception.BookNotFoundException;
import com.betterreads.backend.model.Author;
import com.betterreads.backend.model.Book;
import com.betterreads.backend.repository.AuthorRepository;
import com.betterreads.backend.repository.BookRepository;

@Service
public class BookService {
  private final BookRepository bookRepository;
  private final AuthorRepository authorRepository;

  public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
    this.bookRepository = bookRepository;
    this.authorRepository = authorRepository;
  }

  public BookResponseDto getBookById(Long id) {
    Book book = bookRepository.findById(id)
        .orElseThrow(() -> new BookNotFoundException("Book not found"));
    return mapToDto(book);
  }

  public PaginatedResponseDto<BookResponseDto> getAllBooks(String title, Pageable pageable) {
    Page<Book> page = bookRepository.findByTitleContainingIgnoreCase(title, pageable);
    List<BookResponseDto> books = page.getContent().stream()
        .map(this::mapToDto)
        .toList();

    return new PaginatedResponseDto<>(
        books,
        page.getNumber(),
        page.getSize(),
        page.getTotalPages(),
        page.getTotalElements());
  }

  public BookResponseDto createBook(BookRequestDto dto) {
    Set<Author> authors = resolveAuthors(dto.getOpenLibraryAuthorIds());
    String openLibraryId = dto.getOpenLibraryBookId();

    return bookRepository.findByOpenLibraryId(openLibraryId)
        .map(this::mapToDto)
        .orElseGet(() -> {
          Book book = new Book(dto.getTitle(), authors, openLibraryId, dto.getDescription());
          bookRepository.save(book);
          return mapToDto(book);
        });
  }

  public BookResponseDto updateBookById(Long id, BookRequestDto dto) {
    Book book = bookRepository.findById(id)
        .orElseThrow(() -> new BookNotFoundException("Book not found"));

    Set<Author> authors = resolveAuthors(dto.getOpenLibraryAuthorIds());
    book.setTitle(dto.getTitle());
    book.setAuthors(authors);
    book.setOpenLibraryId(dto.getOpenLibraryBookId());
    book.setDescription(dto.getDescription());

    bookRepository.save(book);
    return mapToDto(book);
  }

  public void deleteBookById(Long id) {
    Book book = bookRepository.findById(id)
        .orElseThrow(() -> new BookNotFoundException("Book not found"));
    bookRepository.delete(book);
  }

  private Set<Author> resolveAuthors(Set<String> openLibraryIds) {
    Set<Author> authors = new HashSet<>();
    for (String id : openLibraryIds) {
      Author author = authorRepository.findByOpenLibraryId(id)
          .orElseThrow(() -> new AuthorNotFoundException("Author not found for Open Library ID: " + id));
      authors.add(author);
    }
    return authors;
  }

  private BookResponseDto mapToDto(Book book) {
    Set<AuthorResponseDto> authorDtos = book.getAuthors().stream()
        .map(a -> new AuthorResponseDto(a.getId(), a.getName(), a.getOpenLibraryId(), a.getBio()))
        .collect(java.util.stream.Collectors.toSet());

    return new BookResponseDto(
        book.getId(),
        book.getTitle(),
        authorDtos,
        book.getOpenLibraryId(),
        book.getDescription());
  }
}
