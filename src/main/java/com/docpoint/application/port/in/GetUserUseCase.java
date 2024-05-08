package com.docpoint.application.port.in;

import java.util.List;

import com.docpoint.domain.entity.User;

public interface GetUserUseCase {
	User getUserById(Long userId);

	List<User> getUsers(List<Long> userIds);
}
