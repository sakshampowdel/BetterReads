package com.betterreads.backend.dto;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BookRequestDto {
  @NotBlank
  private String title;
  @NotNull
  private Set<String> openLibraryAuthorIds;
  @NotBlank
  private String openLibraryBookId;

  public BookRequestDto(String title, Set<String> openLibraryAuthorIds, String openLibraryBookId) {
    this.title = title;
    this.openLibraryAuthorIds = openLibraryAuthorIds;
    this.openLibraryBookId = openLibraryBookId;
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
}
