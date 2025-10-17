package com.betterreads.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betterreads.backend.model.BookList;

public interface BookListRepository extends JpaRepository<BookList, Long> {

}
