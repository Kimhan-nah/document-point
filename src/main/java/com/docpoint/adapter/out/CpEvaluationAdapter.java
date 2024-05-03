package com.docpoint.adapter.out;

import java.util.List;
import java.util.Optional;

import com.docpoint.application.port.out.LoadCpEvaluationPort;
import com.docpoint.common.annotation.PersistenceAdapter;
import com.docpoint.domain.entity.CpEvaluation;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.infrastructure.repository.CpEvaluationRepository;

import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class CpEvaluationAdapter implements LoadCpEvaluationPort {
	private final CpEvaluationRepository cpEvaluationRepository;

	@Override
	public List<CpEvaluation> loadByWorkingDocument(WorkingDocument workingDocument) {
		return cpEvaluationRepository.findByWorkingDocumentId(workingDocument.getId())
			.stream()
			.map(CpEvaluationMapper::mapToDomainEntity)
			.toList();
	}

	@Override
	public Optional<CpEvaluation> loadByWorkingDocumentAndUser(WorkingDocument workingDocument, User user) {
		return cpEvaluationRepository
			.findByWorkingDocumentIdAndDocumentReviewerId(workingDocument.getId(), user.getId())
			.map(CpEvaluationMapper::mapToDomainEntity);
	}
}
