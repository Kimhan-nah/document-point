package com.docpoint.adapter.out;

import com.docpoint.adapter.out.mapper.CpEvaluationMapper;
import com.docpoint.application.port.out.SaveCpEvaluationPort;
import com.docpoint.common.annotation.PersistenceAdapter;
import com.docpoint.domain.entity.CpEvaluation;
import com.docpoint.infrastructure.entity.CpEvaluationJpaEntity;
import com.docpoint.infrastructure.repository.CpEvaluationRepository;

import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class SaveCpEvaluationAdapter implements SaveCpEvaluationPort {
	private final CpEvaluationRepository cpEvaluationRepository;

	@Override
	public void save(CpEvaluation cpEvaluation) {
		CpEvaluationJpaEntity cpEvaluationJpaEntity = CpEvaluationMapper.mapToJpaEntity(cpEvaluation);
		cpEvaluationRepository.save(cpEvaluationJpaEntity);
	}
}
