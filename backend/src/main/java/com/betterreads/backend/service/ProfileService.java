package com.betterreads.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.betterreads.backend.dto.BookListPreviewDto;
import com.betterreads.backend.dto.BookPreviewDto;
import com.betterreads.backend.dto.ProfileResponseDto;
import com.betterreads.backend.exception.ProfileNotFoundException;
import com.betterreads.backend.exception.UserNotFoundException;
import com.betterreads.backend.model.Book;
import com.betterreads.backend.model.Profile;
import com.betterreads.backend.model.User;
import com.betterreads.backend.repository.ProfileRepository;

@Service
public class ProfileService {
  private final ProfileRepository profileRepository;
  private static final int MAX_BOOKLISTS = 5;
  private static final int MAX_BOOKS_PER_LIST = 5;

  public ProfileService(ProfileRepository profileRepository) {
    this.profileRepository = profileRepository;
  }

  public ProfileResponseDto getProfileByUser(User user) {
    Profile profile = profileRepository.findByUser(user)
        .orElseThrow(() -> new UserNotFoundException("User not found"));

    return getProfileById(profile.getId());
  }

  public ProfileResponseDto getProfileById(Long profileId) {
    Profile profile = profileRepository.findById(profileId)
        .orElseThrow(() -> new ProfileNotFoundException("Profile not found"));

    List<BookListPreviewDto> limitedBookList = profile.getBookLists().stream()
        .limit(MAX_BOOKLISTS)
        .map(bookList -> new BookListPreviewDto(bookList.getId(), bookList.getName(),
            mapBooksToPreview(bookList.getBooks())))
        .collect(Collectors.toList());

    return new ProfileResponseDto(profile.getId(), profile.getDisplayName(), profile.getBio(), limitedBookList);
  }

  private List<BookPreviewDto> mapBooksToPreview(List<Book> books) {
    return books.stream()
        .limit(MAX_BOOKS_PER_LIST)
        .map(book -> new BookPreviewDto(book.getId(), book.getOpenLibraryId(), book.getTitle()))
        .collect(Collectors.toList());
  }
}
