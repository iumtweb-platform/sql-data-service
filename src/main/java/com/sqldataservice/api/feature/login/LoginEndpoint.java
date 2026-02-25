package com.sqldataservice.api.feature.login;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginEndpoint {
  private final LoginHandler handler;

  public LoginEndpoint(LoginHandler handler) {
    this.handler = handler;
  }

  @PostMapping
  public LoginResponse handle(@RequestBody LoginRequest request) {
    return handler.handle(request.username());
  }
}