package com.sqldataservice.api.repository;

import com.sqldataservice.model.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AnimeRepository extends JpaRepository<Anime, Integer>, JpaSpecificationExecutor<Anime> {
}
