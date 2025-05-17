package com.betterreads.backend.dto;

import java.util.List;

public class PaginatedResponseDto {
    private List<BookResponseDto> data;
    private int page;
    private int size;
    private int totalPages;
    private Long totalElements;

    public PaginatedResponseDto(List<BookResponseDto> data, int page, int size, int totalPages, Long totalElements) {
        this.data = data;
        this.page = page;
        this.size = size;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }

    public List<BookResponseDto> getData() {
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
