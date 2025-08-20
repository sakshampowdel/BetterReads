package com.betterreads.backend.dto;

public class AuthorResponseDto {
  private Long id;
  private String name;
  private String openLibraryId;
  private String bio;

  protected AuthorResponseDto() {
  }

  public AuthorResponseDto(Long id, String name, String openLibraryId, String bio) {
    this.id = id;
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
}
