package com.betterreads.backend.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Profile {
  @Id
  private Long id;
  private String displayName;
  private String bio;
  @OneToOne
  @MapsId
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
  @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<BookList> bookLists = new ArrayList<>();

  protected Profile() {
  }

  public Profile(String displayName, String bio, User user) {
    this.displayName = displayName;
    this.bio = bio;
    this.user = user;
  }

  public Long getId() {
    return id;
  }

  public String getDisplayName() {
    return displayName;
  }

  public String getBio() {
    return bio;
  }

  public User getUser() {
    return user;
  }

  public List<BookList> getBookLists() {
    return bookLists;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public void addBookList(BookList bookList) {
    bookLists.add(bookList);
    bookList.setProfile(this);
  }

  public void removeBookList(BookList bookList) {
    bookLists.remove(bookList);
  }

}
