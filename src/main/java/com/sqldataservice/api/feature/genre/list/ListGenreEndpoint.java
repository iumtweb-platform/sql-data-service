package com.sqldataservice.api.feature.genre.list;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/genre")
public class ListGenreEndpoint {

  private final ListGenreHandler handler;

  public ListGenreEndpoint(ListGenreHandler handler) {
    this.handler = handler;
  }

  @GetMapping
  public ListGenreResponse[] handle() {
    return handler.handle();
  }
}