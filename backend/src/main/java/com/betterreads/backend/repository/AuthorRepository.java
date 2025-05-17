package com.betterreads.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betterreads.backend.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    
}
