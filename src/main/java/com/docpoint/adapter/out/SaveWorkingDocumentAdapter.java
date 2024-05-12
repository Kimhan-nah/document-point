package com.docpoint.adapter.out;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

import com.docpoint.adapter.out.mapper.UserMapper;
import com.docpoint.adapter.out.mapper.WorkingDocumentMapper;
import com.docpoint.application.port.out.SaveWorkingDocumentPort;
import com.docpoint.common.annotation.PersistenceAdapter;
import com.docpoint.domain.entity.DocumentReviewer;
import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.infrastructure.entity.DocumentReviewerJpaEntity;
import com.docpoint.infrastructure.entity.WorkingDocumentJpaEntity;
import com.docpoint.infrastructure.repository.DocumentReviewerRepository;
import com.docpoint.infrastructure.repository.WorkingDocumentRepository;

import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class SaveWorkingDocumentAdapter implements SaveWorkingDocumentPort {
	private final WorkingDocumentRepository workingDocumentRepository;
	private final DocumentReviewerRepository documentReviewerRepository;

	@Override
	@Transactional
	public void save(WorkingDocument workingDocument, List<DocumentReviewer> documentReviewers) {
		WorkingDocumentJpaEntity workingDocumentJpaEntity = WorkingDocumentMapper.mapToJpaEntity(workingDocument);

		Set<Long> reviewerIds = documentReviewerRepository.findAllByWorkingDocument_Id(workingDocument.getId())
			.stream().map(documentReviewer -> documentReviewer.getReviewer().getId()).collect(Collectors.toSet());

		for (DocumentReviewer documentReviewer : documentReviewers) {
			if (reviewerIds.contains(documentReviewer.getReviewer().getId())) {
				reviewerIds.remove(documentReviewer.getReviewer().getId());
				continue;
			}
			new DocumentReviewerJpaEntity(
				documentReviewer.getId(),
				workingDocumentJpaEntity,
				UserMapper.mapToJpaEntity(documentReviewer.getReviewer()));
		}

		for (Long reviewerId : reviewerIds) {
			documentReviewerRepository.deleteByWorkingDocument_IdAndReviewer_Id(workingDocument.getId(), reviewerId);
		}

		WorkingDocumentJpaEntity save = workingDocumentRepository.save(workingDocumentJpaEntity);
	}

	@Override
	public void update(WorkingDocument workingDocument) {
		WorkingDocumentJpaEntity workingDocumentJpaEntity = WorkingDocumentMapper.mapToJpaEntity(workingDocument);
		workingDocumentRepository.save(workingDocumentJpaEntity);
	}
}
