package com.docpoint.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.docpoint.infrastructure.entity.CpEvaluationJpaEntity;

public interface CpEvaluationRepository extends JpaRepository<CpEvaluationJpaEntity, Long> {
}
