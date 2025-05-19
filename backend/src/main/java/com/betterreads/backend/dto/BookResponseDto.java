package com.betterreads.backend.dto;

import java.util.Set;

public class BookResponseDto {
    private Long id;
    private String title;
    private Set<AuthorResponseDto> authors;
    private String isbn;

    public BookResponseDto(Long id, String title, Set<AuthorResponseDto> authors, String isbn) {
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

    public Set<AuthorResponseDto> getAuthor() {
        return authors;
    }

    public String getIsbn() {
        return isbn;
    }
}
