package com.betterreads.backend.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginRequestDto {
  @NotBlank
  private String email;
  @NotBlank
  private String password;

  public LoginRequestDto(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

}
