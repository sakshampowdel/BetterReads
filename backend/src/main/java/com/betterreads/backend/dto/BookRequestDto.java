package com.betterreads.backend.dto;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;

public class BookRequestDto {
    @NotBlank
    private String title;
    @NotBlank
    private Set<Long> authorIds;
    @NotBlank
    private String isbn;

    public BookRequestDto(String title, Set<Long> authorIds, String isbn) {
        this.title = title;
        this.authorIds = authorIds;
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public Set<Long> getAuthorIds() {
        return authorIds;
    }

    public String getIsbn() {
        return isbn;
    }
}
