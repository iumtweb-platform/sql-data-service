package com.sqldataservice.api.feature.login;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sqldataservice.api.repository.AppUserRepository;
import com.sqldataservice.api.validation.NotFoundException;

@Component
class LoginHandler {

	private final AppUserRepository appUserRepository;

	LoginHandler(AppUserRepository appUserRepository) {
		this.appUserRepository = appUserRepository;
	}

	@Transactional(readOnly = true)
	public LoginResponse handle(String username) {
		return appUserRepository.findByUsername(username)
				.map(appUser -> new LoginResponse(appUser.getId()))
				.orElseThrow(() -> new NotFoundException("AppUser", username));
	}
}
