package com.sqldataservice.api.feature.genre.list;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sqldataservice.api.repository.GenreRepository;

@Component
class ListGenreHandler {

  private final GenreRepository genreRepository;

  ListGenreHandler(GenreRepository genreRepository) {
    this.genreRepository = genreRepository;
  }

  @Transactional(readOnly = true)
  public ListGenreResponse[] handle() {
    var genres = genreRepository.findAll();
    return genres.stream()
        .map(genre -> new ListGenreResponse(genre.getId(), genre.getGenre()))
        .toArray(ListGenreResponse[]::new);
  }
}