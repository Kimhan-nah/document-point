package com.docpoint.adapter.out;

import java.util.List;
import java.util.Optional;

import com.docpoint.adapter.out.mapper.DocumentReviewerMapper;
import com.docpoint.application.port.out.LoadDocumentReviewerPort;
import com.docpoint.common.annotation.PersistenceAdapter;
import com.docpoint.domain.entity.DocumentReviewer;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.infrastructure.repository.DocumentReviewerRepository;

import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class DocumentReviewerAdapter implements LoadDocumentReviewerPort {
	private final DocumentReviewerRepository documentReviewerRepository;

	@Override
	public List<DocumentReviewer> loadByWorkingDocumentId(long workingDocumentId) {
		return documentReviewerRepository.findAllByWorkingDocument_Id(workingDocumentId)
			.stream()
			.map(DocumentReviewerMapper::mapToDomainEntity)
			.toList();
	}

	@Override
	public Optional<DocumentReviewer> loadByWorkingDocumentAndUser(WorkingDocument workingDocument, User user) {
		return documentReviewerRepository
			.findByWorkingDocument_IdAndReviewer_Id(workingDocument.getId(), user.getId())
			.map(DocumentReviewerMapper::mapToDomainEntity);
	}
}
