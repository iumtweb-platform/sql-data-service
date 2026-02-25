package com.sqldataservice.api.feature.appuser.detail;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sqldataservice.api.repository.AppUserRepository;
import com.sqldataservice.api.validation.NotFoundException;

@Component
class DetailAppUserHandler {

	private final AppUserRepository appUserRepository;

	DetailAppUserHandler(AppUserRepository appUserRepository) {
		this.appUserRepository = appUserRepository;
	}

	@Transactional(readOnly = true)
	public DetailAppUserResponse handle(int id) {
		return appUserRepository.findById(id)
				.map(appUser -> {
					String gender = appUser.getGender() != null ? appUser.getGender().getGender() : null;
					String country = appUser.getCountry() != null ? appUser.getCountry().getCountry() : null;
					return new DetailAppUserResponse(appUser.getUsername(), gender, country,
							appUser.getBirthday(), appUser.getJoinedDate());
				})
				.orElseThrow(() -> new NotFoundException("AppUser", String.valueOf(id)));
	}
}
