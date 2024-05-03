package com.docpoint.adapter.out.mapper;

import com.docpoint.domain.entity.CpEvaluation;
import com.docpoint.infrastructure.entity.CpEvaluationJpaEntity;

public class CpEvaluationMapper {
	public static CpEvaluation mapToDomainEntity(CpEvaluationJpaEntity cpEvaluation) {
		return CpEvaluation.builder()
			.id(cpEvaluation.getId())
			.documentReviewer(cpEvaluation.isDocumentReviewerEmpty() ? null :
				DocumentReviewerMapper.mapToDomainEntity(cpEvaluation.getDocumentReviewer()))
			.cp(cpEvaluation.getCp())
			.comment(cpEvaluation.getComment())
			.isDeleted(cpEvaluation.getIsDeleted())
			.build();
	}
}
