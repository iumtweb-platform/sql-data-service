package com.sqldataservice.api.feature.anime.list;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sqldataservice.api.repository.AnimeRepository;

@Component
class ListAnimeHandler {

  private final AnimeRepository animeRepository;

  ListAnimeHandler(AnimeRepository animeRepository) {
    this.animeRepository = animeRepository;
  }

  @Transactional(readOnly = true)
  public ListAnimeResponse handle(ListAnimeQuery query) {
    int elementsPerPage = Math.max(1, query.elementsPerPage());
    int page = Math.max(0, query.page());
    long totalElements = animeRepository.count();
    int totalPages = (int) Math.ceil((double) totalElements / elementsPerPage);
    int fromIndex = Math.min(page * elementsPerPage, (int) totalElements);

    var animeList = animeRepository
        .findAll(Sort.by(Sort.Direction.DESC, "year"))
        .stream()
        .filter(anime -> anime.getYear() != null)
        .sorted((a, b) -> b.getYear().compareTo(a.getYear()))
        .skip(fromIndex)
        .limit(elementsPerPage)
        .map(anime -> new ListAnimeSummary(anime.getId(), anime.getTitle(), anime.getYear().intValue(),
            anime.getImageUrl(), anime.getType().getType(), anime.getSynopsis()))
        .toList();

    return new ListAnimeResponse(elementsPerPage, page, totalElements, totalPages,
        animeList.toArray(new ListAnimeSummary[0]));
  }
}