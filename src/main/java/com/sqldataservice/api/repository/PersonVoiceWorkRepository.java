package com.sqldataservice.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sqldataservice.model.PersonVoiceWork;

public interface PersonVoiceWorkRepository extends JpaRepository<PersonVoiceWork, Integer> {
  List<PersonVoiceWork> findAllByIdAnimeId(int animeId);
}
