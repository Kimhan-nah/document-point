package com.docpoint.application.port.out;

import java.util.Optional;

import com.docpoint.domain.model.User;

public interface LoadUserPort {
	Optional<User> loadById(long userId);
}
