package com.sqldataservice.api.repository;

import com.sqldataservice.model.Character;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, Integer> {
  List<Character> findAllByIdIn(int[] ids);
}
