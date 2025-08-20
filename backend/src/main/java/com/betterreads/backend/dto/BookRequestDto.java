package com.betterreads.backend.dto;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class BookRequestDto {
  @NotBlank
  private String title;
  @NotEmpty
  private Set<String> openLibraryAuthorIds;
  @NotBlank
  private String openLibraryBookId;
  @NotBlank
  private String description;

  protected BookRequestDto() {
  }

  public BookRequestDto(String title, Set<String> openLibraryAuthorIds, String openLibraryBookId, String description) {
    this.title = title;
    this.openLibraryAuthorIds = openLibraryAuthorIds;
    this.openLibraryBookId = openLibraryBookId;
    this.description = description;
  }

  public String getTitle() {
    return title;
  }

  public Set<String> getOpenLibraryAuthorIds() {
    return openLibraryAuthorIds;
  }

  public String getOpenLibraryBookId() {
    return openLibraryBookId;
  }

  public String getDescription() {
    return description;
  }
}
