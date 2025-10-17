package com.betterreads.backend.dto;

import java.util.List;

public class BookListPreviewDto {
  private Long id;
  private String name;
  private List<BookPreviewDto> books;

  public BookListPreviewDto(Long id, String name, List<BookPreviewDto> books) {
    this.id = id;
    this.name = name;
    this.books = books;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public List<BookPreviewDto> getBooks() {
    return books;
  }
}
