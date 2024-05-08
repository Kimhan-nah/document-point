package com.docpoint.application.port.in;

import java.util.List;

import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.Working;

public interface GetWorkingsUseCase {
	List<Working> getWorkingsByTitle(User user, String search);

	Working getWorkingById(Long workingId);
}
