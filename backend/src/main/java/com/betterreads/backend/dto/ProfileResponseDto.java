package com.betterreads.backend.dto;

import java.util.List;

public class ProfileResponseDto {
  private Long id;
  private String displayName;
  private String bio;
  private List<BookListPreviewDto> bookLists;

  public ProfileResponseDto(Long id, String displayName, String bio, List<BookListPreviewDto> bookLists) {
    this.id = id;
    this.displayName = displayName;
    this.bio = bio;
    this.bookLists = bookLists;
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

  public List<BookListPreviewDto> getBookLists() {
    return bookLists;
  }
}
