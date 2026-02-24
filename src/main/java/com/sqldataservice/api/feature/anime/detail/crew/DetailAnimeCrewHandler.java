package com.sqldataservice.api.feature.anime.detail.crew;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sqldataservice.api.repository.PersonAnimeWorkRepository;
import com.sqldataservice.api.validation.NotFoundException;

@Component
class DetailAnimeCrewHandler {

  private final PersonAnimeWorkRepository personAnimeWorkRepository;

  DetailAnimeCrewHandler(PersonAnimeWorkRepository personAnimeWorkRepository) {
    this.personAnimeWorkRepository = personAnimeWorkRepository;
  }

  @Transactional(readOnly = true)
  public DetailAnimeCrewResponse[] handle(DetailAnimeCrewQuery query) {

    List<DetailAnimeCrewResponse> crew = personAnimeWorkRepository.findAllByIdAnimeId(query.id())
        .stream()
        .map(personAnimeWork -> new DetailAnimeCrewResponse(
            personAnimeWork.getPerson().getId(),
            personAnimeWork.getPerson().getName(),
            personAnimeWork.getPerson().getImageUrl(),
            personAnimeWork.getPosition()))
        .toList();

    if (crew.isEmpty()) {
      throw new NotFoundException("Person Anime Work", String.valueOf(query.id()));
    }

    return crew.toArray(DetailAnimeCrewResponse[]::new);
  }
}
