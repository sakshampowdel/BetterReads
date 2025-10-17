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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookController {
  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<BookResponseDto> getBookById(@PathVariable Long id) {
    BookResponseDto response = bookService.getBookById(id);
    return ResponseEntity.ok(response);
  }

  @GetMapping
  public ResponseEntity<PaginatedResponseDto<BookResponseDto>> getAllBooks(
      @RequestParam(required = false, defaultValue = "") String title,
      Pageable pageable) {
    PaginatedResponseDto<BookResponseDto> response = bookService.getAllBooks(title, pageable);
    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<BookResponseDto> createBook(@Valid @RequestBody BookRequestDto dto) {
    BookResponseDto response = bookService.createBook(dto);
    return ResponseEntity.status(201).body(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<BookResponseDto> updateBookById(
      @PathVariable Long id,
      @Valid @RequestBody BookRequestDto dto) {
    BookResponseDto response = bookService.updateBookById(id, dto);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBookById(@PathVariable Long id) {
    bookService.deleteBookById(id);
    return ResponseEntity.noContent().build();
  }
}
