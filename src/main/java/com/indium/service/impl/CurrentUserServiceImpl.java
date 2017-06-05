package com.indium.service.impl;

import org.springframework.stereotype.Service;

import com.indium.domain.CurrentUser;
import com.indium.domain.Role;
import com.indium.service.CurrentUserService;

@Service
public class CurrentUserServiceImpl implements CurrentUserService {

	@Override
	public boolean canAccessUser(CurrentUser currentUser, Long userId) {
		return currentUser != null && (currentUser.getRole() == Role.ADMIN || currentUser.getId().equals(userId));
	}

}
