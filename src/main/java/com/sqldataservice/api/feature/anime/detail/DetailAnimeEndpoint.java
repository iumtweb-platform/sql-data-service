package com.sqldataservice.api.feature.anime.detail;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/anime/{id}")
public class DetailAnimeEndpoint {
  private final DetailAnimeHandler handler;

  public DetailAnimeEndpoint(DetailAnimeHandler handler) {
    this.handler = handler;
  }

  @GetMapping
  public DetailAnimeResponse handle(DetailAnimeQuery query) {
    return handler.handle(query);
  }
}