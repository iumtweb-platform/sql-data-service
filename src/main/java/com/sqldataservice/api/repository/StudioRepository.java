package com.sqldataservice.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sqldataservice.model.Studio;

public interface StudioRepository extends JpaRepository<Studio, Integer> {
}
