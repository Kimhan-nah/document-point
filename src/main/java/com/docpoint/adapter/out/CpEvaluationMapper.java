package com.docpoint.adapter.out;

import java.util.List;

import com.docpoint.domain.entity.CpEvaluation;
import com.docpoint.infrastructure.entity.CpEvaluationJpaEntity;

public class CpEvaluationMapper {
	public static CpEvaluation mapToDomainEntity(CpEvaluationJpaEntity cpEvaluationEntity) {
		return CpEvaluation.builder()
			.id(cpEvaluationEntity.getId())
			// TODO 수정
			// .documentReviewer(cpEvaluationEntity.getDocumentReviewer())
			.cp(cpEvaluationEntity.getCp())
			.comment(cpEvaluationEntity.getComment())
			.isDeleted(cpEvaluationEntity.getIsDeleted())
			.build();
	}

	public static List<CpEvaluation> mapToDomainEntities(List<CpEvaluationJpaEntity> cpEvaluations) {
		// TODO 수정
		return null;
	}
}
