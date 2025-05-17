package com.betterreads.backend.dto;

public class BookRequestDto {
    private String title;
    private String author;
    private String isbn;

    public BookRequestDto(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }
}
