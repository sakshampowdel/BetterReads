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
  private String openLibraryId;
  private String description;

  protected Book() {
  }

  public Book(String title, Set<Author> authors, String openLibraryId, String description) {
    this.title = title;
    this.authors = authors;
    this.openLibraryId = openLibraryId;
    this.description = description;
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

  public String getOpenLibraryId() {
    return openLibraryId;
  }

  public String getDescription() {
    return description;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setAuthors(Set<Author> authors) {
    this.authors = authors;
  }

  public void setOpenLibraryId(String openLibraryId) {
    this.openLibraryId = openLibraryId;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
