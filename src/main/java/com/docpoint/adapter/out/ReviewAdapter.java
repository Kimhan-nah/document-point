package com.docpoint.adapter.out;

import java.util.List;
import java.util.Optional;

import com.docpoint.adapter.out.mapper.ReviewMapper;
import com.docpoint.application.port.out.LoadReviewPort;
import com.docpoint.common.annotation.PersistenceAdapter;
import com.docpoint.domain.entity.Review;
import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.infrastructure.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class ReviewAdapter implements LoadReviewPort {
	private final ReviewRepository reviewRepository;

	@Override
	public Optional<Review> load(long reviewId) {
		return reviewRepository.findById(reviewId)
			.map(ReviewMapper::mapToDomainEntity);
	}

	@Override
	public List<Review> loadByWorkingDocument(WorkingDocument workingDocument) {
		return reviewRepository.findAllByWorkingDocument(workingDocument.getId())
			.stream()
			.map(ReviewMapper::mapToDomainEntity)
			.toList();
	}
}
