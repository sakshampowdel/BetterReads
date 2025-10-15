package com.betterreads.backend.dto;

public class JwtResponseDto {
  private String token;
  private UserResponseDto userResponseDto;

  public JwtResponseDto(String token, UserResponseDto userResponseDto) {
    this.token = token;
    this.userResponseDto = userResponseDto;
  }

  public String getToken() {
    return token;
  }

  public UserResponseDto getUserResponseDto() {
    return userResponseDto;
  }
}
