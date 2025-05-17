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
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(final AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> getAuthor(@PathVariable Long id) {
        AuthorResponseDto authorResponseDto = authorService.getAuthorById(id);
        return ResponseEntity.ok(authorResponseDto);
    }

    @GetMapping()
    public ResponseEntity<PaginatedResponseDto<AuthorResponseDto>> getAuthors(Pageable pageable) {
        PaginatedResponseDto<AuthorResponseDto> paginatedResponseDto = authorService.getAllAuthors(pageable);
        return ResponseEntity.ok(paginatedResponseDto);
    }

    @PostMapping()
    public ResponseEntity<AuthorResponseDto> createAuthor(
        @Valid @RequestBody AuthorRequestDto authorRequestDto) {
        AuthorResponseDto authorResponseDto = authorService.createAuthor(authorRequestDto);
        return ResponseEntity.status(201).body(authorResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> updateAuthor(
        @PathVariable Long id,
        @Valid @RequestBody AuthorRequestDto authorRequestDto) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        return null;
    }
}
