package com.docpoint.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.docpoint.infrastructure.entity.DocumentReviewerJpaEntity;

public interface DocumentReviewerRepository extends JpaRepository<DocumentReviewerJpaEntity, Long> {
	List<DocumentReviewerJpaEntity> findAllByWorkingDocument_Id(long workingDocumentId);

	Optional<DocumentReviewerJpaEntity> findByWorkingDocument_IdAndReviewer_Id(long workingDocumentId, long reviewerId);

	void deleteByWorkingDocument_IdAndReviewer_Id(long workingDocumentId, long reviewerId);
}
