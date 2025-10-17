package com.betterreads.backend.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class BookList {
  @Id
  @GeneratedValue
  private Long id;
  private String name;
  @ManyToOne
  private Profile profile;
  @ManyToMany
  private List<Book> books = new ArrayList<>();

  protected BookList() {
  }

  public BookList(String name, Profile profile) {
    this.name = name;
    this.profile = profile;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Profile getProfile() {
    return profile;
  }

  public List<Book> getBooks() {
    return books;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setProfile(Profile profile) {
    this.profile = profile;
  }

  public void addBook(Book book) {
    if (!books.contains(book)) {
      books.add(book);
    }
  }

  public void removeBook(Book book) {
    books.remove(book);
  }
}
