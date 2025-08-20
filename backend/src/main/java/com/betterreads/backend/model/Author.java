package com.betterreads.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Author {
  @Id
  @GeneratedValue
  private Long id;
  private String name;
  @Column(unique = true, nullable = false)
  private String openLibraryId;
  @Column(length = 5000)
  private String bio;

  protected Author() {
  }

  public Author(String name, String openLibraryId, String bio) {
    this.name = name;
    this.openLibraryId = openLibraryId;
    this.bio = bio;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getOpenLibraryId() {
    return openLibraryId;
  }

  public String getBio() {
    return bio;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setOpenLibraryId(String openLibraryId) {
    this.openLibraryId = openLibraryId;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Author))
      return false;
    Author other = (Author) o;
    return openLibraryId != null && openLibraryId.equals(other.openLibraryId);
  }

  @Override
  public int hashCode() {
    return openLibraryId != null ? openLibraryId.hashCode() : 0;
  }
}
