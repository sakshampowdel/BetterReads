package com.betterreads.backend.dto;

public class ReviewResponseDto {
  private Long id;
  private String profileDisplayName;
  private int rating;
  private String comment;

  public ReviewResponseDto(Long id, String profileDisplayName, int rating, String comment) {
    this.id = id;
    this.profileDisplayName = profileDisplayName;
    this.rating = rating;
    this.comment = comment;
  }

  public Long getId() {
    return id;
  }

  public String getProfileDisplayName() {
    return profileDisplayName;
  }

  public int getRating() {
    return rating;
  }

  public String getComment() {
    return comment;
  }
}
