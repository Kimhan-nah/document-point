package com.docpoint.adapter.out;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.docpoint.application.port.out.LoadReceivedRequestPort;
import com.docpoint.application.port.out.dto.WorkingDocumentWithReviewDto;
import com.docpoint.common.annotation.PersistenceAdapter;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.type.DocStatusType;
import com.docpoint.infrastructure.repository.WorkingDocumentRepository;

import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class LoadReceivedRequestAdapter implements LoadReceivedRequestPort {
	private final WorkingDocumentRepository workingDocumentRepository;

	@Override
	public Page<WorkingDocumentWithReviewDto> loadByUser(User user, Pageable pageable) {
		return workingDocumentRepository.findWithReviewByUserId(user.getId(), pageable);
	}

	@Override
	public Page<WorkingDocumentWithReviewDto> loadByUserWithExcludeStatus(User user, Pageable pageable,
		DocStatusType status) {
		return workingDocumentRepository.findByUserIdWithExcludeStatus(user.getId(), pageable, status);
	}
}
