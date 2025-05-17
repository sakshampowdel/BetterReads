package com.betterreads.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betterreads.backend.dto.AuthorResponseDto;
import com.betterreads.backend.service.AuthorService;

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
}
