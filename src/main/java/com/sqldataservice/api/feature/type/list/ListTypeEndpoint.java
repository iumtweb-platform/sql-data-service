package com.sqldataservice.api.feature.type.list;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sqldataservice.api.shared.KeyValueItem;

@RestController
@RequestMapping("/api/type")
public class ListTypeEndpoint {

  private final ListTypeHandler handler;

  public ListTypeEndpoint(ListTypeHandler handler) {
    this.handler = handler;
  }

  @GetMapping
  public KeyValueItem[] handle() {
    return handler.handle();
  }
}