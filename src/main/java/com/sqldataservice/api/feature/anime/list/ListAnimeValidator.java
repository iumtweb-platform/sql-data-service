package com.sqldataservice.api.feature.anime.list;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sqldataservice.api.repository.GenreRepository;
import com.sqldataservice.api.repository.TypeRepository;
import com.sqldataservice.api.validation.QueryValidationError;

@Component
class ListAnimeValidator {

  private final TypeRepository typeRepository;
  private final GenreRepository genreRepository;

  ListAnimeValidator(TypeRepository typeRepository, GenreRepository genreRepository) {
    this.typeRepository = typeRepository;
    this.genreRepository = genreRepository;
  }

  public List<QueryValidationError> validate(ListAnimeQuery query) {
    List<QueryValidationError> errors = new ArrayList<>();

    if (query.page() < 0 || query.elementsPerPage() <= 0) {
      if (query.page() < 0) {
        errors.add(new QueryValidationError("page", "must be greater than or equal to 0"));
      }
      if (query.elementsPerPage() <= 0) {
        errors.add(new QueryValidationError("elementsPerPage", "must be greater than 0"));
      }
    }
    if (query.sortBy() != null && !query.sortBy().isEmpty()) {
      if (!query.sortBy().equalsIgnoreCase("title") && !query.sortBy().equalsIgnoreCase("year")) {
        errors.add(new QueryValidationError("sortBy", "must be one of: title, year"));
      }
    }
    if (query.sortDirection() != null && !query.sortDirection().isEmpty()) {
      if (!query.sortDirection().equalsIgnoreCase("asc") && !query.sortDirection().equalsIgnoreCase("desc")) {
        errors.add(new QueryValidationError("sortDirection", "must be one of: asc, desc"));
      }
    }
    if (query.types() != null && !query.types().isEmpty()) {
      String[] typeIds = query.types().split(",");
      for (String typeId : typeIds) {
        try {
          int id = Integer.parseInt(typeId.trim());
          if (!typeRepository.existsById(id)) {
            errors.add(new QueryValidationError("types", "contains unknown id: " + id));
          }
        } catch (NumberFormatException e) {
          errors.add(new QueryValidationError("types", "must contain only comma-separated integer ids"));
          break;
        }
      }
    }
    if (query.genres() != null && !query.genres().isEmpty()) {
      String[] genreIds = query.genres().split(",");
      for (String genreId : genreIds) {
        try {
          int id = Integer.parseInt(genreId.trim());
          if (!genreRepository.existsById(id)) {
            errors.add(new QueryValidationError("genres", "contains unknown id: " + id));
          }
        } catch (NumberFormatException e) {
          errors.add(new QueryValidationError("genres", "must contain only comma-separated integer ids"));
          break;
        }
      }
    }
    return errors;
  }
}
