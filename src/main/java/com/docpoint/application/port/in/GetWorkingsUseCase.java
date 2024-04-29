package com.docpoint.application.port.in;

import java.util.List;

import com.docpoint.domain.model.User;
import com.docpoint.domain.model.Working;

public interface GetWorkingsUseCase {
	List<Working> getWorkingsByTitle(User user, String search);
}
