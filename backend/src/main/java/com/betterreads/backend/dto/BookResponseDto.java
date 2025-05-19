package com.betterreads.backend.dto;

import java.util.Set;

import com.betterreads.backend.model.Author;

public class BookResponseDto {
    private Long id;
    private String title;
    private Set<Author> authors;
    private String isbn;

    public BookResponseDto(Long id, String title, Set<Author> authors, String isbn) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.isbn = isbn;
    }

    public Long getId() {
        return id;
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
