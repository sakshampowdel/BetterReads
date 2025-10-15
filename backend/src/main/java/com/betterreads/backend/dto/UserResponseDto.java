package com.betterreads.backend.dto;

public class UserResponseDto {
  private Long id;
  private String displayName;
  private String email;

  public UserResponseDto(Long id, String displayName, String email) {
    this.id = id;
    this.displayName = displayName;
    this.email = email;
  }

  public Long getId() {
    return id;
  }

  public String getDisplayName() {
    return displayName;
  }

  public String getEmail() {
    return email;
  }
}
