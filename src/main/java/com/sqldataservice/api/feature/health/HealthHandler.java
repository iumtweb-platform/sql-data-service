package com.sqldataservice.api.feature.health;

import org.springframework.stereotype.Component;

@Component
class HealthHandler {
  public HealthResponse handle() {
    return new HealthResponse("OK");
  }
}
