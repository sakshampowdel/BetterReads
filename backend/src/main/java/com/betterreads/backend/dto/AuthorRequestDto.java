package com.betterreads.backend.dto;

import jakarta.validation.constraints.NotBlank;

public class AuthorRequestDto {
    @NotBlank
    private String name;
    @NotBlank
    private String openLibraryId;

    public AuthorRequestDto(String name, String openLibraryId) {
        this.name = name;
        this.openLibraryId = openLibraryId;
    }

    public String getName() {
        return name;
    }

    public String getOpenLibraryId() {
        return openLibraryId;
    }
}
