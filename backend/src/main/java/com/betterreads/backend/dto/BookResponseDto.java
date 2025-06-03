package com.betterreads.backend.dto;

import java.util.Set;

public class BookResponseDto {
    private Long id;
    private String title;
    private Set<AuthorResponseDto> authors;
    private String openLibraryBookId;

    public BookResponseDto(Long id, String title, Set<AuthorResponseDto> authors, String openLibraryBookId) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.openLibraryBookId = openLibraryBookId;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Set<AuthorResponseDto> getAuthors() {
        return authors;
    }

    public String getOpenLibraryBookId() {
        return openLibraryBookId;
    }
}
