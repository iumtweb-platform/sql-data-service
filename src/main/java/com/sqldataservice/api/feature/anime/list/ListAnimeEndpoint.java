package com.sqldataservice.api.feature.anime.list;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sqldataservice.api.validation.InvalidQueryException;

@RestController
@RequestMapping("/api/anime")
public class ListAnimeEndpoint {

  private final ListAnimeHandler handler;
  private final ListAnimeValidator validator;

  public ListAnimeEndpoint(ListAnimeHandler handler, ListAnimeValidator validator) {
    this.handler = handler;
    this.validator = validator;
  }

  @GetMapping
  public ListAnimeResponse handle(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "20") int elementsPerPage,
      @RequestParam(required = false) String search,
      @RequestParam(defaultValue = "year") String sortBy,
      @RequestParam(defaultValue = "desc") String sortDirection,
      @RequestParam(required = false) String types,
      @RequestParam(required = false) String genres) {

    var query = new ListAnimeQuery(page, elementsPerPage, search, sortBy, sortDirection, types, genres);
    var validationErrors = validator.validate(query);

    if (!validationErrors.isEmpty()) {
      throw new InvalidQueryException("Invalid query parameters", validationErrors);
    }

    return handler.handle(query);
  }
}