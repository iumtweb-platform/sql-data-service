package com.sqldataservice.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sqldataservice.model.PersonAnimeWork;

public interface PersonAnimeWorkRepository extends JpaRepository<PersonAnimeWork, Integer> {
  List<PersonAnimeWork> findAllByIdAnimeId(int animeId);
}
