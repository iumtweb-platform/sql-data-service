package com.sqldataservice.api.feature.appuser.detail;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/appuser/{id}")
public class DetailAppUserEndpoint {
  private final DetailAppUserHandler handler;

  public DetailAppUserEndpoint(DetailAppUserHandler handler) {
    this.handler = handler;
  }

  @GetMapping
  public DetailAppUserResponse handle(@PathVariable int id) {
    return handler.handle(id);
  }
}