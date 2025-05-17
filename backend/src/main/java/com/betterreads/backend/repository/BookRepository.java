package com.betterreads.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betterreads.backend.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    
}
