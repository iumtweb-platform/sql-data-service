package com.sqldataservice.api.feature.health;

import org.springframework.stereotype.Component;

@Component
class HealthHandler {
  public HealthResponse handle() {
    // In a real implementation, you would check the actual health of the
    // application and database
    return new HealthResponse("OK");
  }
}
