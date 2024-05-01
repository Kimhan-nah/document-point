package com.docpoint.domain.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.docpoint.application.port.in.GetWorkingsUseCase;
import com.docpoint.application.port.out.LoadUserWorkingsPort;
import com.docpoint.domain.model.User;
import com.docpoint.domain.model.Working;
import com.docpoint.domain.type.WorkingStatusType;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class GetWorkingsService implements GetWorkingsUseCase {
	private final LoadUserWorkingsPort loadUserWorkingsPort;

	/**
	 *
	 * @param user
	 * @param search
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Working> getWorkingsByTitle(User user, String search) {
		return loadUserWorkingsPort.loadByStatusIsNotAndTitle(user.getId(), WorkingStatusType.WAITING, search);
	}
}
