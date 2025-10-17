package com.betterreads.backend.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.betterreads.backend.dto.PaginatedResponseDto;
import com.betterreads.backend.dto.ReviewRequestDto;
import com.betterreads.backend.dto.ReviewResponseDto;
import com.betterreads.backend.exception.BookNotFoundException;
import com.betterreads.backend.exception.ProfileNotFoundException;
import com.betterreads.backend.model.Book;
import com.betterreads.backend.model.Profile;
import com.betterreads.backend.model.Review;
import com.betterreads.backend.model.User;
import com.betterreads.backend.repository.BookRepository;
import com.betterreads.backend.repository.ProfileRepository;
import com.betterreads.backend.repository.ReviewRepository;

@Service
public class ReviewService {
  private final ReviewRepository reviewRepository;
  private final BookRepository bookRepository;
  private final ProfileRepository profileRepository;

  public ReviewService(ReviewRepository reviewRepository, BookRepository bookRepository,
      ProfileRepository profileRepository) {
    this.reviewRepository = reviewRepository;
    this.bookRepository = bookRepository;
    this.profileRepository = profileRepository;
  }

  public ReviewResponseDto createReview(ReviewRequestDto dto, User user) {
    Profile profile = profileRepository.findByUser(user)
        .orElseThrow(() -> new ProfileNotFoundException("Profile not found"));
    Book book = bookRepository.findById(dto.getBookId())
        .orElseThrow(() -> new BookNotFoundException("Book not found"));

    Review review = new Review(book, profile, dto.getRating(), dto.getComment());
    reviewRepository.save(review);
    return mapToDto(review);
  }

  public PaginatedResponseDto<ReviewResponseDto> getReviewsForBook(Long bookId, Pageable pageable) {
    Page<Review> page = reviewRepository.findByBook_Id(bookId, pageable);
    List<ReviewResponseDto> reviews = page.getContent().stream()
        .map(this::mapToDto)
        .toList();

    return new PaginatedResponseDto<>(
        reviews,
        page.getNumber(),
        page.getSize(),
        page.getTotalPages(),
        page.getTotalElements());
  }

  private ReviewResponseDto mapToDto(Review review) {
    return new ReviewResponseDto(
        review.getId(),
        review.getProfile().getDisplayName(),
        review.getRating(),
        review.getComment());
  }
}
