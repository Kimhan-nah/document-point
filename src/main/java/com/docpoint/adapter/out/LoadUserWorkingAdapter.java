package com.docpoint.adapter.out;

import java.util.List;
import java.util.Optional;

import com.docpoint.adapter.out.mapper.WorkingMapper;
import com.docpoint.application.port.out.LoadUserWorkingPort;
import com.docpoint.common.annotation.PersistenceAdapter;
import com.docpoint.domain.entity.Working;
import com.docpoint.infrastructure.repository.WorkingRepository;

import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class LoadUserWorkingAdapter implements LoadUserWorkingPort {
	private final WorkingRepository workingRepository;

	@Override
	public List<Working> loadByTitle(long userId, String search) {
		return workingRepository.searchUserWorking(userId, search)
			.stream()
			.map(WorkingMapper::mapToDomainEntity)
			.toList();
	}

	@Override
	public Optional<Working> load(long workingId) {
		return workingRepository.findById(workingId)
			.map(WorkingMapper::mapToDomainEntity);
	}
}
