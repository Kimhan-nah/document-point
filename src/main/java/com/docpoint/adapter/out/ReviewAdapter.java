package com.docpoint.adapter.out;

import java.util.List;

import com.docpoint.adapter.out.mapper.ReviewMapper;
import com.docpoint.application.port.out.LoadReviewPort;
import com.docpoint.application.port.out.SaveReviewPort;
import com.docpoint.common.annotation.PersistenceAdapter;
import com.docpoint.domain.entity.DocumentReviewer;
import com.docpoint.domain.entity.Review;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.infrastructure.entity.ReviewJpaEntity;
import com.docpoint.infrastructure.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class ReviewAdapter implements LoadReviewPort, SaveReviewPort {
	private final ReviewRepository reviewRepository;

	@Override
	public List<Review> loadByWorkingDocument(WorkingDocument workingDocument) {
		return reviewRepository.findAllByWorkingDocument(workingDocument.getId())
			.stream()
			.map(ReviewMapper::mapToDomainEntity)
			.toList();
	}

	@Override
	public boolean existsReview(WorkingDocument workingDocument, User reviewer) {
		return reviewRepository.existsByWorkingDocumentAndReviewer(workingDocument.getId(), reviewer.getId());
	}

	@Override
	public void save(Review review) {
		ReviewJpaEntity reviewJpaEntity = ReviewMapper.mapToJpaEntity(review);
		reviewRepository.save(reviewJpaEntity);
	}

	@Override
	public List<Review> loadUserReviewOfDocument(DocumentReviewer documentReviewer) {
		return reviewRepository.findAllByDocumentReviewer_IdAndIsDeletedFalse(documentReviewer.getId())
			.stream()
			.map(ReviewMapper::mapToDomainEntity)
			.toList();
	}
}
