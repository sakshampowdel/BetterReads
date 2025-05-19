package com.betterreads.backend.dto;

import java.util.Set;

import com.betterreads.backend.model.Author;

import jakarta.validation.constraints.NotBlank;

public class BookRequestDto {
    @NotBlank
    private String title;
    @NotBlank
    private Set<Author> authors;
    @NotBlank
    private String isbn;

    public BookRequestDto(String title, Set<Author> authors, String isbn) {
        this.title = title;
        this.authors = authors;
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public Set<Author> getAuthor() {
        return authors;
    }

    public String getIsbn() {
        return isbn;
    }
}
