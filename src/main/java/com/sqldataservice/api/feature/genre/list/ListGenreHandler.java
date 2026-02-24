package com.sqldataservice.api.feature.genre.list;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sqldataservice.api.repository.GenreRepository;
import com.sqldataservice.api.shared.KeyValueItem;

@Component
class ListGenreHandler {

  private final GenreRepository genreRepository;

  ListGenreHandler(GenreRepository genreRepository) {
    this.genreRepository = genreRepository;
  }

  @Transactional(readOnly = true)
  public KeyValueItem[] handle() {
    var genres = genreRepository.findAll();
    return genres.stream()
        .map(genre -> new KeyValueItem(genre.getId(), genre.getGenre()))
        .toArray(KeyValueItem[]::new);
  }
}