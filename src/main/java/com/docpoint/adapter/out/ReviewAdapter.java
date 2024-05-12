package com.docpoint.adapter.out;

import java.util.List;
import java.util.Optional;

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
	public boolean existsReviewByReviewer(WorkingDocument workingDocument, User reviewer) {
		return reviewRepository.existsByWorkingDocumentAndReviewer(workingDocument.getId(), reviewer.getId());
	}

	@Override
	public void save(Review review) {
		Optional<ReviewJpaEntity> first = reviewRepository.findAllByDocumentReviewer_IdAndIsDeletedFalse(
				review.getDocumentReviewer().getId()).stream()
			.filter(reviewJpaEntity -> reviewJpaEntity.getQuestion() == review.getQuestion())
			.findFirst();

		if (first.isPresent()) {
			ReviewJpaEntity reviewJpaEntity = first.get();
			reviewJpaEntity.updateAnswer(review.getAnswer());
			reviewRepository.save(reviewJpaEntity);
			return;
		}
		ReviewJpaEntity reviewJpaEntity = ReviewMapper.mapToJpaEntity(review);
		reviewRepository.save(reviewJpaEntity);
	}

	@Override
	public void delete(DocumentReviewer documentReviewer) {
		Long id = documentReviewer.getId();
		List<ReviewJpaEntity> reviews = reviewRepository.findAllByDocumentReviewer_IdAndIsDeletedFalse(
			documentReviewer.getId());
		for (ReviewJpaEntity review : reviews) {
			review.delete();
		}
		reviewRepository.saveAll(reviews);
	}

	@Override
	public List<Review> loadUserReviewOfDocument(DocumentReviewer documentReviewer) {
		return reviewRepository.findAllByDocumentReviewer_IdAndIsDeletedFalse(documentReviewer.getId())
			.stream()
			.map(ReviewMapper::mapToDomainEntity)
			.toList();
	}

	@Override
	public boolean existsReview(WorkingDocument workingDocument) {
		return reviewRepository.existsByWorkingDocument(workingDocument.getId());
	}
}
