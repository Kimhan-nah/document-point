package com.docpoint.adapter.out;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.docpoint.adapter.out.mapper.WorkingDocumentMapper;
import com.docpoint.application.port.out.LoadUserWorkingDocumentPort;
import com.docpoint.common.annotation.PersistenceAdapter;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.infrastructure.repository.WorkingDocumentRepository;

import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class LoadUserWorkingDocumentAdapter implements LoadUserWorkingDocumentPort {
	private final WorkingDocumentRepository workingDocumentRepository;

	@Override
	public Page<WorkingDocument> loadByUser(User user, Pageable pageable) {
		return workingDocumentRepository.findByUserId(user.getId(), pageable)
			.map(WorkingDocumentMapper::mapToDomainEntity);
	}
}
