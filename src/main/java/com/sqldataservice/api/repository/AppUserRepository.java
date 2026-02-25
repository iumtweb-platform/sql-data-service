package com.sqldataservice.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sqldataservice.model.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
  Optional<AppUser> findByUsername(String username);

}
