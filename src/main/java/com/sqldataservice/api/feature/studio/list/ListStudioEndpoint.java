package com.sqldataservice.api.feature.studio.list;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sqldataservice.api.shared.KeyValueItem;

@RestController
@RequestMapping("/api/studio")
public class ListStudioEndpoint {

  private final ListStudioHandler handler;

  public ListStudioEndpoint(ListStudioHandler handler) {
    this.handler = handler;
  }

  @GetMapping
  public KeyValueItem[] handle() {
    return handler.handle();
  }
}