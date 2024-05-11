package com.docpoint.adapter.out;

import com.docpoint.adapter.out.mapper.DocumentReviewerMapper;
import com.docpoint.application.port.out.SaveDocumentReviewerPort;
import com.docpoint.common.annotation.PersistenceAdapter;
import com.docpoint.domain.entity.DocumentReviewer;
import com.docpoint.infrastructure.entity.DocumentReviewerJpaEntity;
import com.docpoint.infrastructure.repository.DocumentReviewerRepository;

import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class SaveDocumentReviewerAdapter implements SaveDocumentReviewerPort {
	private final DocumentReviewerRepository documentReviewerRepository;

	@Override
	public void save(DocumentReviewer documentReviewer) {
		DocumentReviewerJpaEntity documentReviewerJpaEntity = DocumentReviewerMapper.mapToJpaEntity(documentReviewer);
		documentReviewerRepository.save(documentReviewerJpaEntity);
	}
}
