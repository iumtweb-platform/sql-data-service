package com.sqldataservice.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sqldataservice.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
