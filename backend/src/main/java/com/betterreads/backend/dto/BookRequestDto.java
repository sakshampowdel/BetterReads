package com.betterreads.backend.dto;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BookRequestDto {
    @NotBlank
    private String title;
    @NotNull
    private Set<String> openLibraryAuthorIds;
    @NotBlank
    private String isbn;

    public BookRequestDto(String title, Set<String> openLibraryAuthorIds, String isbn) {
        this.title = title;
        this.openLibraryAuthorIds = openLibraryAuthorIds;
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public Set<String> getOpenLibraryAuthorIds() {
        return openLibraryAuthorIds;
    }

    public String getIsbn() {
        return isbn;
    }
}
