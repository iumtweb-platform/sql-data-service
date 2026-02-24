package com.sqldataservice.api.feature.anime.detail.crew;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/anime/{id}/crew")
public class DetailAnimeCrewEndpoint {
  private final DetailAnimeCrewHandler handler;

  public DetailAnimeCrewEndpoint(DetailAnimeCrewHandler handler) {
    this.handler = handler;
  }

  @GetMapping
  public DetailAnimeCrewResponse[] handle(@PathVariable int id) {
    return handler.handle(id);
  }
}