package com.betterreads.backend.dto;

public class BookPreviewDto {
  private Long id; // internal DB ID
  private String openLibraryId; // external ID for cover & metadata
  private String title;

  public BookPreviewDto(Long id, String openLibraryId, String title) {
    this.id = id;
    this.openLibraryId = openLibraryId;
    this.title = title;
  }

  public Long getId() {
    return id;
  }

  public String getOpenLibraryId() {
    return openLibraryId;
  }

  public String getTitle() {
    return title;
  }
}
