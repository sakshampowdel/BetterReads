package com.betterreads.backend.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  @ManyToMany
  private Set<Author> authors;
  @Column(unique = true, nullable = false)
  private String openLibraryId;
  @Column(length = 5000)
  private String description;

  @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Review> reviews = new HashSet<>();

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

  public Set<Review> getReviews() {
    return reviews;
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

  public void addReview(Review review) {
    reviews.add(review);
    review.setBook(this);
  }

  public void removeReview(Review review) {
    reviews.remove(review);
    review.setBook(null);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Book))
      return false;
    Book other = (Book) o;
    return openLibraryId != null && openLibraryId.equals(other.openLibraryId);
  }

  @Override
  public int hashCode() {
    return openLibraryId != null ? openLibraryId.hashCode() : 0;
  }
}
