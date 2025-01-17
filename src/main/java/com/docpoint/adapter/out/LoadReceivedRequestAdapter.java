package com.docpoint.adapter.out;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.docpoint.adapter.out.mapper.WorkingDocumentMapper;
import com.docpoint.application.port.out.LoadReceivedRequestPort;
import com.docpoint.common.annotation.PersistenceAdapter;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.domain.type.DocStatusType;
import com.docpoint.infrastructure.repository.WorkingDocumentRepository;

import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class LoadReceivedRequestAdapter implements LoadReceivedRequestPort {
	private final WorkingDocumentRepository workingDocumentRepository;

	@Override
	public Page<WorkingDocument> loadByUser(User user, Pageable pageable, DocStatusType status) {
		return workingDocumentRepository.findByReviewerId(user.getId(), pageable, status)
			.map(WorkingDocumentMapper::mapToDomainEntity);
	}

	@Override
	public Page<WorkingDocument> loadByUserWithExcludeStatus(User user, Pageable pageable,
		DocStatusType excludeStatus, DocStatusType status) {
		return workingDocumentRepository.findByUserIdExcludeStatus(user.getId(), pageable, excludeStatus, status)
			.map(WorkingDocumentMapper::mapToDomainEntity);
	}
}
