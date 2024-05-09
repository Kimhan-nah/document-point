package com.docpoint.adapter.out;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.docpoint.adapter.out.mapper.UserMapper;
import com.docpoint.adapter.out.mapper.WorkingDocumentMapper;
import com.docpoint.application.port.out.LoadWorkingDocumentPort;
import com.docpoint.common.annotation.PersistenceAdapter;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.infrastructure.entity.DocumentReviewerJpaEntity;
import com.docpoint.infrastructure.repository.DocumentReviewerRepository;
import com.docpoint.infrastructure.repository.WorkingDocumentRepository;

import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class LoadWorkingDocumentAdapter implements LoadWorkingDocumentPort {
	private final WorkingDocumentRepository workingDocumentRepository;
	private final DocumentReviewerRepository documentReviewerRepository;

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

	@Override
	public List<User> loadReviewers(WorkingDocument workingDocument) {
		return documentReviewerRepository.findAllByWorkingDocument_Id(workingDocument.getId())
			.stream()
			.map(DocumentReviewerJpaEntity::getReviewer)
			.map(UserMapper::mapToDomainEntity)
			.toList();

	}
}
