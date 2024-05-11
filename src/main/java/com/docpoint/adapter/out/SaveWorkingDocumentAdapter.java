package com.docpoint.adapter.out;

import java.util.List;

import com.docpoint.adapter.out.mapper.UserMapper;
import com.docpoint.adapter.out.mapper.WorkingDocumentMapper;
import com.docpoint.application.port.out.SaveWorkingDocumentPort;
import com.docpoint.common.annotation.PersistenceAdapter;
import com.docpoint.domain.entity.DocumentReviewer;
import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.infrastructure.entity.DocumentReviewerJpaEntity;
import com.docpoint.infrastructure.entity.WorkingDocumentJpaEntity;
import com.docpoint.infrastructure.repository.WorkingDocumentRepository;

import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class SaveWorkingDocumentAdapter implements SaveWorkingDocumentPort {
	private final WorkingDocumentRepository workingDocumentRepository;

	@Override
	public void save(WorkingDocument workingDocument, List<DocumentReviewer> documentReviewers) {
		WorkingDocumentJpaEntity workingDocumentJpaEntity = WorkingDocumentMapper.mapToJpaEntity(workingDocument);
		List<DocumentReviewerJpaEntity> list2 = documentReviewers.stream()
			.map(documentReviewer -> new DocumentReviewerJpaEntity(documentReviewer.getId(), workingDocumentJpaEntity,
				UserMapper.mapToJpaEntity(documentReviewer.getReviewer())))
			.toList();

		WorkingDocumentJpaEntity save = workingDocumentRepository.save(workingDocumentJpaEntity);
	}

	@Override
	public void update(WorkingDocument workingDocument) {
		WorkingDocumentJpaEntity workingDocumentJpaEntity = WorkingDocumentMapper.mapToJpaEntity(workingDocument);
		workingDocumentRepository.save(workingDocumentJpaEntity);
	}
}
