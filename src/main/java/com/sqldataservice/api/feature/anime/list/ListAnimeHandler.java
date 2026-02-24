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

  private int[] parseIds(String ids) {
    if (ids == null || ids.isEmpty()) {
      return new int[0];
    }
    String[] parts = ids.split(",");
    int[] result = new int[parts.length];
    for (int i = 0; i < parts.length; i++) {
      result[i] = Integer.parseInt(parts[i].trim());
    }
    return result;
  }

  @Transactional(readOnly = true)
  public ListAnimeResponse handle(ListAnimeQuery query) {
    int elementsPerPage = Math.max(1, query.elementsPerPage());
    int page = Math.max(0, query.page());
    int[] types = query.types() != null ? parseIds(query.types()) : new int[0];
    int[] genres = query.genres() != null ? parseIds(query.genres()) : new int[0];

    var animeList = animeRepository
        .findAll(Sort.by(Sort.Direction.fromString(query.sortDirection()), query.sortBy()))
        .stream()
        .filter(
            anime -> query.search() == null || anime.getTitle().toLowerCase().contains(query.search().toLowerCase()))
        .filter(anime -> types.length == 0 || types[0] == anime.getType().getId()
            || (types.length > 1
                && java.util.Arrays.stream(types).anyMatch(typeId -> typeId == anime.getType().getId())))
        .filter(anime -> genres.length == 0 || (anime.getGenres() != null && anime.getGenres().stream()
            .anyMatch(genre -> java.util.Arrays.stream(genres).anyMatch(genreId -> genreId == genre.getId()))))
        .sorted((a, b) -> b.getYear() != null && a.getYear() != null ? b.getYear().compareTo(a.getYear()) : 0)
        .map(anime -> new ListAnimeResponseContent(anime.getId(), anime.getTitle(),
            anime.getYear() != null ? anime.getYear().intValue() : null,
            anime.getImageUrl(), anime.getType().getType(), anime.getSynopsis()))
        .toList();

    long totalElements = animeList.size();
    int totalPages = (int) Math.ceil((double) totalElements / elementsPerPage);
    int fromIndex = Math.min(page * elementsPerPage, (int) totalElements);

    animeList = animeList.subList(fromIndex, Math.min(fromIndex + elementsPerPage, (int) totalElements));

    return new ListAnimeResponse(elementsPerPage, page, totalElements, totalPages,
        animeList.toArray(new ListAnimeResponseContent[0]));
  }
}