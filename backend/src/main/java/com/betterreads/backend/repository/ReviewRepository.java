package com.betterreads.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betterreads.backend.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
  List<Review> findByBookId(Long bookId);

  List<Review> findByProfileId(Long profileId);
}
