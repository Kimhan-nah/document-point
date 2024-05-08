package com.docpoint.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.docpoint.application.port.in.GetUserUseCase;
import com.docpoint.application.port.out.LoadUserPort;
import com.docpoint.common.annotation.UseCase;
import com.docpoint.common.exception.ErrorType;
import com.docpoint.common.exception.custom.NotFoundException;
import com.docpoint.domain.entity.User;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetUserService implements GetUserUseCase {
	private final LoadUserPort loadUserPort;

	@Override
	@Transactional(readOnly = true)
	public User getUserById(Long userId) {
		User user = loadUserPort.loadById(userId).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_USER));
		if (user.isDeleted()) {
			throw new NotFoundException(ErrorType.NOT_FOUND_USER);
		}
		return user;
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> getUsers(List<Long> userIds) {
		List<User> users = new ArrayList<>();

		for (Long userId : userIds) {
			users.add(getUserById(userId));
		}

		return users;
	}
}
