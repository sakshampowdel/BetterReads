package com.betterreads.backend.dto;

import jakarta.validation.constraints.NotBlank;

public class UserRequestDto {
  @NotBlank
  private String username;
  @NotBlank
  private String email;
  @NotBlank
  private String password;

  public UserRequestDto(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }
}
