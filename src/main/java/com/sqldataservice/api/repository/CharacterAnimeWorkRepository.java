package com.sqldataservice.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sqldataservice.model.CharacterAnimeWork;

public interface CharacterAnimeWorkRepository extends JpaRepository<CharacterAnimeWork, Integer> {
  List<CharacterAnimeWork> findAllByIdAnimeId(int animeId);
}
