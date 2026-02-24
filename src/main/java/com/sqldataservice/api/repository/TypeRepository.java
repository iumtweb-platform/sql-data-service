package com.sqldataservice.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sqldataservice.model.Type;

public interface TypeRepository extends JpaRepository<Type, Integer> {
}
