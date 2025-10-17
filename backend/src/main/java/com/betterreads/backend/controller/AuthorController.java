package com.betterreads.backend.controller;

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

import com.betterreads.backend.dto.AuthorRequestDto;
import com.betterreads.backend.dto.AuthorResponseDto;
import com.betterreads.backend.dto.PaginatedResponseDto;
import com.betterreads.backend.service.AuthorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
  private final AuthorService authorService;

  public AuthorController(AuthorService authorService) {
    this.authorService = authorService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<AuthorResponseDto> getAuthorById(@PathVariable Long id) {
    AuthorResponseDto response = authorService.getAuthorById(id);
    return ResponseEntity.ok(response);
  }

  @GetMapping
  public ResponseEntity<PaginatedResponseDto<AuthorResponseDto>> getAllAuthors(Pageable pageable) {
    PaginatedResponseDto<AuthorResponseDto> response = authorService.getAllAuthors(pageable);
    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<AuthorResponseDto> createAuthor(@Valid @RequestBody AuthorRequestDto dto) {
    AuthorResponseDto response = authorService.createAuthor(dto);
    return ResponseEntity.status(201).body(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<AuthorResponseDto> updateAuthorById(@PathVariable Long id,
      @Valid @RequestBody AuthorRequestDto dto) {
    AuthorResponseDto response = authorService.updateAuthorById(id, dto);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAuthorById(@PathVariable Long id) {
    authorService.deleteAuthorById(id);
    return ResponseEntity.noContent().build();
  }
}
