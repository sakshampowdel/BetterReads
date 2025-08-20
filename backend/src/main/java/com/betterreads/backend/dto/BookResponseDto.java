package com.betterreads.backend.dto;

import java.util.Set;

public class BookResponseDto {
  private Long id;
  private String title;
  private Set<AuthorResponseDto> authors;
  private String openLibraryId;
  private String description;

  protected BookResponseDto() {
  }

  public BookResponseDto(Long id, String title, Set<AuthorResponseDto> authors, String openLibraryId,
      String description) {
    this.id = id;
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

  public Set<AuthorResponseDto> getAuthors() {
    return authors;
  }

  public String getOpenLibraryId() {
    return openLibraryId;
  }

  public String getDescription() {
    return description;
  }
}
