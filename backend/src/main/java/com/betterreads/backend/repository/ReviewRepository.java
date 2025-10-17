package com.betterreads.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.betterreads.backend.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
  Page<Review> findByBook_Id(Long bookId, Pageable pageable);

  Page<Review> findByProfile_Id(Long profileId, Pageable pageable);
}
