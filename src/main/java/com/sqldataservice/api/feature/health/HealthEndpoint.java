package com.sqldataservice.api.feature.health;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/health")
public class HealthEndpoint {
  private final HealthHandler handler;

  public HealthEndpoint(HealthHandler handler) {
    this.handler = handler;
  }

  @RequestMapping
  public HealthResponse handle() {
    return handler.handle();
  }
}
