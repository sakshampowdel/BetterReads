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
    private String isbn;

    protected Book() {}

    public Book(String title, Set<Author> authors, String isbn) {
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(Set<Author> authors) {
        this.authors = authors;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
