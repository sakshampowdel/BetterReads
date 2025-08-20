package com.betterreads.backend.controller;

import com.betterreads.backend.dto.BookRequestDto;
import com.betterreads.backend.dto.BookResponseDto;
import com.betterreads.backend.dto.PaginatedResponseDto;
import com.betterreads.backend.service.BookService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookController {
  private final BookService bookService;

  public BookController(final BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<BookResponseDto> getBook(@PathVariable Long id) {
    BookResponseDto bookResponseDto = bookService.getBookById(id);
    return ResponseEntity.ok(bookResponseDto);
  }

  @GetMapping()
  public ResponseEntity<PaginatedResponseDto<BookResponseDto>> getBooks(Pageable pageable) {
    PaginatedResponseDto<BookResponseDto> paginatedResponseDto = bookService.getAllBooks(pageable);
    return ResponseEntity.ok(paginatedResponseDto);
  }

  @PostMapping()
  public ResponseEntity<BookResponseDto> createBook(
      @Valid @RequestBody BookRequestDto bookRequestDto) {
    BookResponseDto bookResponseDto = bookService.createBook(bookRequestDto);
    return ResponseEntity.status(201).body(bookResponseDto);
  }

  @PutMapping("/{id}")
  public ResponseEntity<BookResponseDto> updateBook(
      @PathVariable Long id,
      @Valid @RequestBody BookRequestDto bookRequestDto) {
    BookResponseDto bookResponseDto = bookService.updateBookById(id, bookRequestDto);
    return ResponseEntity.ok(bookResponseDto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
    bookService.deleteBookById(id);
    return ResponseEntity.noContent().build();
  }
}
