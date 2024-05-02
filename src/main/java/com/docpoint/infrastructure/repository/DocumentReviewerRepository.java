package com.docpoint.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.docpoint.infrastructure.entity.DocumentReviewerJpaEntity;

public interface DocumentReviewerRepository extends JpaRepository<DocumentReviewerJpaEntity, Long> {
}
