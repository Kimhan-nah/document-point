package com.docpoint.domain.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.docpoint.application.port.in.GetWorkingsUseCase;
import com.docpoint.application.port.out.LoadUserWorkingPort;
import com.docpoint.common.annotation.UseCase;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.Working;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
class GetWorkingsService implements GetWorkingsUseCase {
	private final LoadUserWorkingPort loadUserWorkingPort;

	/**
	 *
	 * @param user
	 * @param search
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Working> getWorkingsByTitle(User user, String search) {
		return loadUserWorkingPort.loadByTitle(user.getId(), search);
	}
}
