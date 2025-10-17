package com.betterreads.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Review {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional = false)
  private Book book;

  @ManyToOne(optional = false)
  private Profile profile;

  private int rating; // 1–5
  @Column(length = 5000)
  private String comment;

  protected Review() {
  }

  public Review(Book book, Profile profile, int rating, String comment) {
    this.book = book;
    this.profile = profile;
    this.rating = rating;
    this.comment = comment;
  }

  public Long getId() {
    return id;
  }

  public Book getBook() {
    return book;
  }

  public Profile getProfile() {
    return profile;
  }

  public int getRating() {
    return rating;
  }

  public String getComment() {
    return comment;
  }

  public void setBook(Book book) {
    this.book = book;
  }

  public void setProfile(Profile profile) {
    this.profile = profile;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

}
