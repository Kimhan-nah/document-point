package com.docpoint.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.docpoint.infrastructure.entity.ReviewJpaEntity;

public interface ReviewRepository extends JpaRepository<ReviewJpaEntity, Long>, ReviewRepositoryCustom {
	List<ReviewJpaEntity> findAllByDocumentReviewer_IdAndIsDeletedFalse(long documentReviewerId);
}
