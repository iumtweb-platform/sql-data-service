package com.sqldataservice.api.feature.type.list;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sqldataservice.api.repository.TypeRepository;
import com.sqldataservice.api.shared.KeyValueItem;

@Component
class ListTypeHandler {

  private final TypeRepository typeRepository;

  ListTypeHandler(TypeRepository typeRepository) {
    this.typeRepository = typeRepository;
  }

  @Transactional(readOnly = true)
  public KeyValueItem[] handle() {
    var types = typeRepository.findAll();
    return types.stream()
        .map(type -> new KeyValueItem(type.getId(), type.getType()))
        .toArray(KeyValueItem[]::new);
  }
}