package com.betterreads.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betterreads.backend.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByOpenLibraryId(String openLibraryId);
}
