package com.docpoint.adapter.out;

import java.util.List;
import java.util.Optional;

import com.docpoint.adapter.out.mapper.WorkingMapper;
import com.docpoint.application.port.out.LoadUserWorkingPort;
import com.docpoint.common.annotation.PersistenceAdapter;
import com.docpoint.common.exception.ErrorType;
import com.docpoint.common.exception.custom.NotFoundException;
import com.docpoint.domain.entity.Working;
import com.docpoint.infrastructure.entity.WorkingAssigneeJpaEntity;
import com.docpoint.infrastructure.entity.WorkingJpaEntity;
import com.docpoint.infrastructure.repository.WorkingAssigneeRepository;
import com.docpoint.infrastructure.repository.WorkingRepository;

import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class LoadUserWorkingAdapter implements LoadUserWorkingPort {
	private final WorkingRepository workingRepository;
	private final WorkingAssigneeRepository workingAssigneeRepository;

	@Override
	public List<Working> loadByTitle(long userId, String search) {
		return workingRepository.searchUserWorking(userId, search)
			.stream()
			.map(workingJpaEntity -> {
				WorkingAssigneeJpaEntity assignee = workingAssigneeRepository.findOneByWorkingId(
					workingJpaEntity.getId());
				return WorkingMapper.mapToDomainEntityWithAssignee(workingJpaEntity, assignee);
			})
			.toList();
	}

	@Override
	public Optional<Working> load(long workingId) {
		WorkingJpaEntity working = workingRepository.findById(workingId).orElseThrow(
			() -> new NotFoundException(ErrorType.NOT_FOUND_WORKING));
		// WorkingAssigneeJpaEntity assignee = workingAssigneeRepository.findOneByWorkingId(workingId);

		return Optional.of(WorkingMapper.mapToDomainEntityWithAssignee(working, working.getAssignee()));
	}
}
