package com.betterreads.backend.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;

public class AuthorRequestDto {
  @NotBlank
  private String name;
  @NotBlank
  private String openLibraryId;
  @Nullable
  private String bio;

  protected AuthorRequestDto() {
  }

  public AuthorRequestDto(String name, String openLibraryId, String bio) {
    this.name = name;
    this.openLibraryId = openLibraryId;
    this.bio = bio;
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
}
