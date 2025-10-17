package com.betterreads.backend.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.betterreads.backend.dto.AuthorRequestDto;
import com.betterreads.backend.dto.AuthorResponseDto;
import com.betterreads.backend.dto.PaginatedResponseDto;
import com.betterreads.backend.exception.AuthorNotFoundException;
import com.betterreads.backend.model.Author;
import com.betterreads.backend.repository.AuthorRepository;

@Service
public class AuthorService {
  private final AuthorRepository authorRepository;

  public AuthorService(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  public AuthorResponseDto getAuthorById(Long id) {
    Author author = authorRepository.findById(id)
        .orElseThrow(() -> new AuthorNotFoundException("Author not found"));
    return mapToDto(author);
  }

  public PaginatedResponseDto<AuthorResponseDto> getAllAuthors(Pageable pageable) {
    Page<Author> page = authorRepository.findAll(pageable);

    List<AuthorResponseDto> authors = page.getContent().stream()
        .map(this::mapToDto)
        .toList();

    return new PaginatedResponseDto<>(
        authors,
        page.getNumber(),
        page.getSize(),
        page.getTotalPages(),
        page.getTotalElements());
  }

  public AuthorResponseDto createAuthor(AuthorRequestDto dto) {
    return authorRepository.findByOpenLibraryId(dto.getOpenLibraryId())
        .map(this::mapToDto)
        .orElseGet(() -> {
          Author author = new Author(dto.getName(), dto.getOpenLibraryId(), dto.getBio());
          authorRepository.save(author);
          return mapToDto(author);
        });
  }

  public AuthorResponseDto updateAuthorById(Long id, AuthorRequestDto dto) {
    Author author = authorRepository.findById(id)
        .orElseThrow(() -> new AuthorNotFoundException("Author not found"));

    author.setName(dto.getName());
    author.setOpenLibraryId(dto.getOpenLibraryId());
    author.setBio(dto.getBio());

    authorRepository.save(author);
    return mapToDto(author);
  }

  public void deleteAuthorById(Long id) {
    Author author = authorRepository.findById(id)
        .orElseThrow(() -> new AuthorNotFoundException("Author not found"));
    authorRepository.delete(author);
  }

  private AuthorResponseDto mapToDto(Author author) {
    return new AuthorResponseDto(
        author.getId(),
        author.getName(),
        author.getOpenLibraryId(),
        author.getBio());
  }
}
