package com.sqldataservice.api.feature.studio.list;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sqldataservice.api.repository.StudioRepository;
import com.sqldataservice.api.shared.KeyValueItem;

@Component
class ListStudioHandler {

  private final StudioRepository studioRepository;

  ListStudioHandler(StudioRepository studioRepository) {
    this.studioRepository = studioRepository;
  }

  @Transactional(readOnly = true)
  public KeyValueItem[] handle() {
    var studios = studioRepository.findAll();
    return studios.stream()
        .map(studio -> new KeyValueItem(studio.getId(), studio.getStudio()))
        .toArray(KeyValueItem[]::new);
  }
}