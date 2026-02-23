package com.sqldataservice.api.feature.anime.list;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/anime")
public class ListAnimeEndpoint {

  private final ListAnimeHandler handler;

  public ListAnimeEndpoint(ListAnimeHandler handler) {
    this.handler = handler;
  }

  @GetMapping
  public ListAnimeResponse handle(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "20") int elementsPerPage) {
    return handler.handle(new ListAnimeQuery(page, elementsPerPage));
  }
}