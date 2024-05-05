package com.docpoint.adapter.out;

import java.util.List;

import com.docpoint.adapter.out.mapper.WorkingMapper;
import com.docpoint.application.port.out.LoadUserWorkingPort;
import com.docpoint.common.annotation.PersistenceAdapter;
import com.docpoint.domain.entity.Working;
import com.docpoint.domain.type.WorkingStatusType;
import com.docpoint.infrastructure.repository.WorkingRepository;

import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class LoadUserWorkingAdapter implements LoadUserWorkingPort {
	private final WorkingRepository workingRepository;

	@Override
	public List<Working> loadByStatusIsNotAndTitle(long userId, WorkingStatusType status, String search) {
		return workingRepository.searchUserWorking(userId, status, search)
			.stream()
			.map(WorkingMapper::mapToDomainEntity)
			.toList();
	}
}
