package com.betterreads.backend.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betterreads.backend.dto.PaginatedResponseDto;
import com.betterreads.backend.dto.ReviewRequestDto;
import com.betterreads.backend.dto.ReviewResponseDto;
import com.betterreads.backend.model.User;
import com.betterreads.backend.service.ReviewService;

@RestController
@RequestMapping("api/reviews")
public class ReviewController {
  private final ReviewService reviewService;

  public ReviewController(ReviewService reviewService) {
    this.reviewService = reviewService;
  }

  @GetMapping("/book/{bookId}")
  public ResponseEntity<PaginatedResponseDto<ReviewResponseDto>> getReviewsForBook(@PathVariable Long bookId,
      Pageable pageable) {
    PaginatedResponseDto<ReviewResponseDto> paginatedResponseDto = reviewService.getReviewsForBook(bookId, pageable);
    return ResponseEntity.ok(paginatedResponseDto);
  }

  @PostMapping
  public ResponseEntity<ReviewResponseDto> createReview(@RequestBody ReviewRequestDto reviewRequestDto,
      Authentication authentication) {
    User user = (User) authentication.getPrincipal();
    ReviewResponseDto reviewResponseDto = reviewService.createReview(reviewRequestDto, user);
    return ResponseEntity.status(201).body(reviewResponseDto);
  }

}
