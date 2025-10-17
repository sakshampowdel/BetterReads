package com.betterreads.backend.dto;

public class ReviewRequestDto {
  private Long bookId;
  private int rating;
  private String comment;

  public ReviewRequestDto() {
  }

  public ReviewRequestDto(Long bookId, int rating, String comment) {
    this.bookId = bookId;
    this.rating = rating;
    this.comment = comment;
  }

  public Long getBookId() {
    return bookId;
  }

  public int getRating() {
    return rating;
  }

  public String getComment() {
    return comment;
  }
}
