package com.docpoint.application.port.out;

import java.util.Optional;

import com.docpoint.domain.entity.User;

public interface LoadUserPort {
	Optional<User> loadById(long userId);
}
