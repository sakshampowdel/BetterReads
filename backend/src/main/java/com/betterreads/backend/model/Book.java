package com.betterreads.backend.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToMany
    private Set<Author> authors;
    private String openLibraryBookId;

    protected Book() {}

    public Book(String title, Set<Author> authors, String openLibraryBookId) {
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

    public Set<Author> getAuthors() {
        return authors;
    }

    public String getOpenLibraryBookId() {
        return openLibraryBookId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public void setOpenLibraryBookId(String isbn) {
        this.openLibraryBookId = isbn;
    }
}
