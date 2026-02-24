package com.sqldataservice.api.feature.anime.list;

import java.util.Arrays;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sqldataservice.api.repository.AnimeRepository;
import com.sqldataservice.model.Anime;

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

  private Specification<Anime> buildSpecification(ListAnimeQuery query, int[] types, int[] genres) {
    return (root, queryRoot, builder) -> {
      queryRoot.distinct(true);
      var predicates = builder.conjunction();

      if (query.search() != null && !query.search().isBlank()) {
        predicates = builder.and(predicates,
            builder.like(builder.lower(root.get("title")), "%" + query.search().toLowerCase() + "%"));
      }

      if (types.length > 0) {
        predicates = builder.and(predicates, root.get("type").get("id").in(Arrays.stream(types).boxed().toList()));
      }

      if (genres.length > 0) {
        var genresJoin = root.join("genres");
        predicates = builder.and(predicates,
            genresJoin.get("id").in(Arrays.stream(genres).boxed().toList()));
      }

      return predicates;
    };
  }

  @Transactional(readOnly = true)
  public ListAnimeResponse handle(ListAnimeQuery query) {
    int[] types = query.types() != null ? parseIds(query.types()) : new int[0];
    int[] genres = query.genres() != null ? parseIds(query.genres()) : new int[0];

    var specification = buildSpecification(query, types, genres);
    var sortDirection = Sort.Direction.fromString(query.sortDirection());
    var sort = "year".equalsIgnoreCase(query.sortBy())
        ? Sort.by(new Sort.Order(sortDirection, query.sortBy()).nullsLast())
        : Sort.by(sortDirection, query.sortBy());
    var pageRequest = PageRequest.of(query.page(), query.elementsPerPage(), sort);
    var animePage = animeRepository.findAll(specification, pageRequest);

    var animeList = animePage
        .stream()
        .map(anime -> new ListAnimeResponseContent(anime.getId(), anime.getTitle(),
            anime.getYear() != null ? anime.getYear().intValue() : null,
            anime.getImageUrl(), anime.getType().getType(), anime.getSynopsis()))
        .toList();

    long totalElements = animePage.getTotalElements();
    int totalPages = animePage.getTotalPages();

    return new ListAnimeResponse(query.elementsPerPage(), query.page(), totalElements, totalPages,
        animeList.toArray(new ListAnimeResponseContent[0]));
  }
}