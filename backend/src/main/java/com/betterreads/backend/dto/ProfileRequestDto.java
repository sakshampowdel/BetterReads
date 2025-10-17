package com.betterreads.backend.dto;

import jakarta.validation.constraints.Size;

public class ProfileRequestDto {
  @Size(max = 50, message = "Display name must be at most 50 characters")
  private String displayName;

  @Size(max = 500, message = "Bio must be at most 500 characters")
  private String bio;

  public ProfileRequestDto() {
  }

  public ProfileRequestDto(String displayName, String bio) {
    this.displayName = displayName;
    this.bio = bio;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getBio() {
    return bio;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }
}
