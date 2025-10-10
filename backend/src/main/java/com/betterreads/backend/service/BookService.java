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
import com.betterreads.backend.exception.AuthorNotFoundException;
import com.betterreads.backend.exception.BookNotFoundException;
import com.betterreads.backend.model.Author;
import com.betterreads.backend.model.Book;

@Service
public class BookService {
  private final BookRepository bookRepository;
  private final AuthorRepository authorRepository;

  public BookService(final BookRepository bookRepository, final AuthorRepository authorRepository) {
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

  public PaginatedResponseDto<BookResponseDto> getAllBooks(String title, Pageable pageable) {
    Page<Book> page = bookRepository.findByTitleContainingIgnoreCase(title, pageable);
    List<Book> books = page.getContent();
    List<BookResponseDto> bookResponseDtos = new ArrayList<>();

    for (Book book : books) {
      bookResponseDtos.add(mapToResponseDto(book));
    }

    PaginatedResponseDto<BookResponseDto> paginatedResponseDto = new PaginatedResponseDto<>(bookResponseDtos,
        page.getNumber(), page.getSize(), page.getTotalPages(), page.getTotalElements());

    return paginatedResponseDto;
  }

  public BookResponseDto createBook(BookRequestDto bookRequestDto) {
    String title = bookRequestDto.getTitle();
    Set<Author> author = new HashSet<>();

    for (String openLibraryId : bookRequestDto.getOpenLibraryAuthorIds()) {
      Optional<Author> maybeAuthor = authorRepository.findByOpenLibraryId(openLibraryId);

      if (maybeAuthor.isEmpty()) {
        throw new AuthorNotFoundException("Author with Open Library ID " + openLibraryId + " does not exist.");
      }

      author.add(maybeAuthor.get());

    }

    String openLibraryId = bookRequestDto.getOpenLibraryBookId();

    String description = bookRequestDto.getDescription();

    Optional<Book> duplicateBook = bookRepository.findByOpenLibraryId(openLibraryId);
    if (duplicateBook.isPresent()) {
      return mapToResponseDto(duplicateBook.get());
    }

    Book book = new Book(title, author, openLibraryId, description);

    bookRepository.save(book);

    return mapToResponseDto(book);
  }

  public BookResponseDto updateBookById(Long id, BookRequestDto bookRequestDto) {
    Optional<Book> maybeBook = bookRepository.findById(id);
    if (maybeBook.isEmpty()) {
      throw new BookNotFoundException("Book with id " + id + " doesn't exist!");
    }

    Book book = maybeBook.get();

    String title = bookRequestDto.getTitle();
    Set<Author> author = new HashSet<>();

    for (String openLibraryId : bookRequestDto.getOpenLibraryAuthorIds()) {
      Optional<Author> maybeAuthor = authorRepository.findByOpenLibraryId(openLibraryId);

      if (maybeAuthor.isEmpty()) {
        throw new AuthorNotFoundException("Author with Open Library ID " + openLibraryId + " does not exist.");
      }

      author.add(maybeAuthor.get());

    }

    String openLibraryId = bookRequestDto.getOpenLibraryBookId();
    String description = bookRequestDto.getDescription();

    book.setTitle(title);
    book.setAuthors(author);
    book.setOpenLibraryId(openLibraryId);
    book.setDescription(description);

    bookRepository.save(book);

    return mapToResponseDto(book);
  }

  public void deleteBookById(Long id) {
    Optional<Book> maybeBook = bookRepository.findById(id);
    if (maybeBook.isEmpty()) {
      throw new BookNotFoundException("Book with id " + id + " doesn't exist!");
    }

    bookRepository.deleteById(id);
  }

  public BookResponseDto mapToResponseDto(Book book) {
    Set<Author> authors = book.getAuthors();
    Set<AuthorResponseDto> authorResponseDtos = new HashSet<>();
    for (Author author : authors) {
      authorResponseDtos
          .add(new AuthorResponseDto(author.getId(), author.getName(), author.getOpenLibraryId(), author.getBio()));
    }

    return new BookResponseDto(book.getId(), book.getTitle(), authorResponseDtos, book.getOpenLibraryId(),
        book.getDescription());
  }

}
