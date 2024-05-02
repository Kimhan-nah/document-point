package com.docpoint.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.docpoint.infrastructure.entity.WorkingDocumentJpaEntity;

public interface WorkingDocumentRepository extends JpaRepository<WorkingDocumentJpaEntity, Long> {
}
