package com.betterreads.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betterreads.backend.dto.BookListRequestDto;
import com.betterreads.backend.dto.BookListResponseDto;
import com.betterreads.backend.model.User;
import com.betterreads.backend.service.BookListService;

@RestController
@RequestMapping("/api/booklists")
public class BookListController {
  private final BookListService bookListService;

  public BookListController(BookListService bookListService) {
    this.bookListService = bookListService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<BookListResponseDto> getBookList(@PathVariable Long id) {
    BookListResponseDto response = bookListService.getBookListById(id);
    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<BookListResponseDto> createBookList(@RequestBody BookListRequestDto bookListRequestDto,
      Authentication authentication) {
    User user = (User) authentication.getPrincipal();
    BookListResponseDto bookListResponseDto = bookListService.createBookList(user, bookListRequestDto.getName());
    return ResponseEntity.status(201).body(bookListResponseDto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBookList(@PathVariable Long id, Authentication authentication) {
    User user = (User) authentication.getPrincipal();
    bookListService.deleteBookList(id, user);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/{listId}/books/{bookId}")
  public ResponseEntity<Void> addBookToList(
      @PathVariable Long listId,
      @PathVariable Long bookId,
      Authentication authentication) {
    User user = (User) authentication.getPrincipal();
    bookListService.addBookToList(listId, bookId, user);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{listId}/books/{bookId}")
  public ResponseEntity<Void> removeBookFromList(
      @PathVariable Long listId,
      @PathVariable Long bookId,
      Authentication authentication) {
    User user = (User) authentication.getPrincipal();
    bookListService.removeBookFromList(listId, bookId, user);
    return ResponseEntity.noContent().build();
  }

}
