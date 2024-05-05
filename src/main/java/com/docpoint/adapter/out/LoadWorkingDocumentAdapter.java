package com.docpoint.adapter.out;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.docpoint.adapter.out.mapper.WorkingDocumentMapper;
import com.docpoint.application.port.out.LoadWorkingDocumentPort;
import com.docpoint.common.annotation.PersistenceAdapter;
import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.infrastructure.repository.WorkingDocumentRepository;

import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class LoadWorkingDocumentAdapter implements LoadWorkingDocumentPort {
	private final WorkingDocumentRepository workingDocumentRepository;

	@Override
	public Page<WorkingDocument> loadByTeamId(long teamId, Pageable pageable) {
		return workingDocumentRepository.findByTeamId(teamId, pageable)
			.map(WorkingDocumentMapper::mapToDomainEntity);
	}

	@Override
	public Optional<WorkingDocument> loadById(long workingDocumentId) {
		return workingDocumentRepository.findById(workingDocumentId)
			.map(WorkingDocumentMapper::mapToDomainEntity);
	}

}
