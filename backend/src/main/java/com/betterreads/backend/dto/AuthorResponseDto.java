package com.betterreads.backend.dto;

public class AuthorResponseDto {
    private Long id;
    private String name;

    public AuthorResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
