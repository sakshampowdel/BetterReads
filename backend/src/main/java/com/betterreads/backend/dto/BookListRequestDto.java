package com.betterreads.backend.dto;

public class BookListRequestDto {
  private String name;

  public BookListRequestDto(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
