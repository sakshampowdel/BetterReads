package com.betterreads.backend.dto;

import jakarta.validation.constraints.NotBlank;

public class UserRequestDto {
  @NotBlank
  private String displayName;
  @NotBlank
  private String email;
  @NotBlank
  private String password;

  public UserRequestDto(String displayName, String email, String password) {
    this.displayName = displayName;
    this.email = email;
    this.password = password;
  }

  public String getDisplayName() {
    return displayName;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }
}
