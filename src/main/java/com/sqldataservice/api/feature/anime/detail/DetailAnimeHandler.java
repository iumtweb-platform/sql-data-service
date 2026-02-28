package com.sqldataservice.api.feature.anime.detail;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sqldataservice.api.repository.AnimeRepository;
import com.sqldataservice.api.repository.CharacterAnimeWorkRepository;
import com.sqldataservice.api.repository.CharacterRepository;
import com.sqldataservice.api.repository.PersonVoiceWorkRepository;
import com.sqldataservice.api.shared.AnimeSummaryItem;
import com.sqldataservice.api.shared.KeyValueItem;
import com.sqldataservice.api.validation.NotFoundException;
import com.sqldataservice.model.PersonVoiceWork;
import com.sqldataservice.model.Character;

@Component
class DetailAnimeHandler {

	private final AnimeRepository animeRepository;
	private final CharacterRepository characterRepository;
	private final PersonVoiceWorkRepository personVoiceWorkRepository;
	private final CharacterAnimeWorkRepository characterAnimeWorkRepository;

	DetailAnimeHandler(AnimeRepository animeRepository, PersonVoiceWorkRepository personVoiceWorkRepository,
			CharacterAnimeWorkRepository characterAnimeWorkRepository, CharacterRepository characterRepository) {

		this.characterAnimeWorkRepository = characterAnimeWorkRepository;
		this.animeRepository = animeRepository;
		this.personVoiceWorkRepository = personVoiceWorkRepository;
		this.characterRepository = characterRepository;

	}

	@Transactional(readOnly = true)
	public DetailAnimeResponse handle(int id) {

		return animeRepository.findById(id).map(anime -> {
			KeyValueItem[] genres = anime.getGenres().stream().map(g -> new KeyValueItem(g.getId(), g.getGenre()))
					.toArray(KeyValueItem[]::new);
			KeyValueItem[] studios = anime.getStudios().stream().map(s -> new KeyValueItem(s.getId(), s.getStudio()))
					.toArray(KeyValueItem[]::new);
			KeyValueItem[] themes = anime.getThemes().stream().map(t -> new KeyValueItem(t.getId(), t.getTheme()))
					.toArray(KeyValueItem[]::new);
			KeyValueItem[] demographics = anime.getDemographics().stream()
					.map(d -> new KeyValueItem(d.getId(), d.getDemographic())).toArray(KeyValueItem[]::new);
			KeyValueItem source = new KeyValueItem(anime.getSource().getId(), anime.getSource().getSource());
			KeyValueItem[] licensors = anime.getLicensors().stream()
					.map(l -> new KeyValueItem(l.getId(), l.getLicensor()))
					.toArray(KeyValueItem[]::new);
			KeyValueItem[] producers = anime.getProducers().stream()
					.map(p -> new KeyValueItem(p.getId(), p.getProducer()))
					.toArray(KeyValueItem[]::new);
			KeyValueItem[] streamingServices = anime.getStreamingServices().stream()
					.map(s -> new KeyValueItem(s.getId(), s.getStreamingService())).toArray(KeyValueItem[]::new);
			KeyValueItem type = new KeyValueItem(anime.getType().getId(), anime.getType().getType());
			KeyValueItem rating = anime.getRating() != null
					? new KeyValueItem(anime.getRating().getId(), anime.getRating().getRating())
					: null;
			KeyValueItem status = new KeyValueItem(anime.getStatus().getId(), anime.getStatus().getStatus());

			int[] characterIds = characterAnimeWorkRepository.findAllByIdAnimeId(anime.getId()).stream()
					.map(caw -> caw.getId().getCharacterId()).mapToInt(Integer::intValue).toArray();

			HashMap<Integer, Character> characters = characterRepository.findAllByIdIn(characterIds).stream()
					.collect(HashMap::new, (map, c) -> map.put(c.getId(), c), HashMap::putAll);

			HashMap<Integer, PersonVoiceWork[]> personByCharacterId = personVoiceWorkRepository
					.findAllByIdAnimeId(anime.getId()).stream()
					.collect(HashMap::new, (map, p) -> map.compute(p.getCharacter().getId(), (key, existing) -> {
						if (existing == null) {
							return new PersonVoiceWork[] { p };
						} else {
							return Stream.concat(Stream.of(existing), Stream.of(p)).toArray(PersonVoiceWork[]::new);
						}
					}), HashMap::putAll);

			DetailAnimeResponseVoice[] detailCharacters = characters.values().stream()
					.map(c -> {
						PersonVoiceWork[] works = personByCharacterId.get(c.getId());
						DetailAnimeResponseVoicePerson[] voices = (works == null || works.length == 0)
								? new DetailAnimeResponseVoicePerson[0]
								: Arrays.stream(works)
										.map(pvw -> new DetailAnimeResponseVoicePerson(
												pvw.getPerson().getId(),
												pvw.getPerson().getName(),
												pvw.getPerson().getImageUrl(),
												pvw.getLanguage().getLanguage()))
										.toArray(DetailAnimeResponseVoicePerson[]::new);

						return new DetailAnimeResponseVoice(
								new DetialAnimeResponseVoiceCharacter(c.getId(), c.getName(), c.getImageUrl()),
								voices);

					}).toArray(DetailAnimeResponseVoice[]::new);

			AnimeSummaryItem[] recommendations = anime.getAnimesForRecommendedAnimeId().stream()
					.map(r -> new AnimeSummaryItem(r.getId(), r.getTitle(),
							r.getYear() != null ? r.getYear().intValue() : null, r.getImageUrl(), r.getType().getType(),
							r.getScore(),
							r.getSynopsis()))
					.toArray(AnimeSummaryItem[]::new);

			return new DetailAnimeResponse(anime.getId(), anime.getTitle(), anime.getSynopsis(),
					anime.getImageUrl(), type, anime.getEpisodes(), status,
					rating, genres, studios, themes, demographics, source, licensors, producers,
					streamingServices, recommendations, detailCharacters);
		}).orElseThrow(() -> new NotFoundException("Anime", String.valueOf(id)));
	}
}
