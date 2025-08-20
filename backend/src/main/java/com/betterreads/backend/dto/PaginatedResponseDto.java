package com.betterreads.backend.dto;

import java.util.List;

public class PaginatedResponseDto<T> {
  private List<T> data;
  private int page;
  private int size;
  private int totalPages;
  private Long totalElements;

  public PaginatedResponseDto(List<T> data, int page, int size, int totalPages, Long totalElements) {
    this.data = data;
    this.page = page;
    this.size = size;
    this.totalPages = totalPages;
    this.totalElements = totalElements;
  }

  public List<T> getData() {
    return data;
  }

  public int getPage() {
    return page;
  }

  public int getSize() {
    return size;
  }

  public int getTotalPages() {
    return totalPages;
  }

  public Long getTotalElements() {
    return totalElements;
  }
}
