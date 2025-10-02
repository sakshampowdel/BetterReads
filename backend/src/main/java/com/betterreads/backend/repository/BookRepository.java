package com.betterreads.backend.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.betterreads.backend.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
  Optional<Book> findByOpenLibraryId(String openLibraryId);

  Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
