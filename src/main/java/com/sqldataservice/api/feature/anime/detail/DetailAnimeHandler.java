package com.sqldataservice.api.feature.anime.detail;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sqldataservice.api.repository.AnimeRepository;
import com.sqldataservice.api.validation.NotFoundException;

@Component
class DetailAnimeHandler {

  private final AnimeRepository animeRepository;

  DetailAnimeHandler(AnimeRepository animeRepository) {
    this.animeRepository = animeRepository;
  }

  @Transactional(readOnly = true)
  public DetailAnimeResponse handle(DetailAnimeQuery query) {

    return animeRepository.findById(query.id()).map(anime -> {
      String[] genres = anime.getGenres().stream().map(g -> g.getGenre()).toArray(String[]::new);
      String[] studios = anime.getStudios().stream().map(s -> s.getStudio()).toArray(String[]::new);
      return new DetailAnimeResponse(anime.getId(), anime.getTitle(), anime.getSynopsis(),
          anime.getImageUrl(), anime.getType().getType(), anime.getEpisodes().intValue(), anime.getStatus().getStatus(),
          anime.getRating().getRating(), genres, studios, new DetailAnimeResponseCharacter[0]);
    }).orElseThrow(() -> new NotFoundException("Anime", String.valueOf(query.id())));
  }
}
