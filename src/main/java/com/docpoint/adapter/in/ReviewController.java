package com.docpoint.adapter.in;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.docpoint.adapter.in.dto.AllReviewsResponseDto;
import com.docpoint.adapter.in.dto.ReviewRequestDto;
import com.docpoint.adapter.in.dto.ReviewResponseDto;
import com.docpoint.adapter.in.dto.UserDto;
import com.docpoint.application.port.in.DeleteReviewUseCase;
import com.docpoint.application.port.in.GetReviewsUseCase;
import com.docpoint.application.port.in.GetWorkingDocumentUseCase;
import com.docpoint.application.port.in.RegisterReviewUseCase;
import com.docpoint.common.annotation.LoginUser;
import com.docpoint.common.annotation.WebAdapter;
import com.docpoint.domain.entity.Evaluation;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@WebAdapter
@RequiredArgsConstructor
@Validated
@RequestMapping("/workingdocs/{workingId}")
public class ReviewController {
	private final GetReviewsUseCase getReviewsUseCase;
	private final GetWorkingDocumentUseCase getWorkingDocumentUseCase;
	private final RegisterReviewUseCase registerReviewUseCase;
	private final DeleteReviewUseCase deleteReviewUseCase;

	@GetMapping("/review")
	public ResponseEntity<ReviewResponseDto> getReview(@PathVariable @Positive Long workingId, @LoginUser User user) {
		WorkingDocument workingDocument = getWorkingDocumentUseCase.getWorkingDocument(workingId);
		UserDto reviewer = UserDto.toDto(user);
		List<Evaluation> review = getReviewsUseCase.getReview(workingDocument, user);

		return ResponseEntity.ok(ReviewResponseDto.of(reviewer, review));
	}

	@GetMapping("/reviews")
	public ResponseEntity<AllReviewsResponseDto> getReviews(@PathVariable @Positive Long workingId,
		@LoginUser User user) {
		WorkingDocument workingDocument = getWorkingDocumentUseCase.getWorkingDocument(workingId);
		Map<User, List<Evaluation>> allReviews = getReviewsUseCase.getAllReviews(workingDocument, user);

		List<ReviewResponseDto> reviews = allReviews.entrySet().stream()
			.map(entry -> ReviewResponseDto.of(UserDto.toDto(entry.getKey()), entry.getValue()))
			.toList();
		AllReviewsResponseDto response = AllReviewsResponseDto.of(reviews);

		return ResponseEntity.ok(response);
	}

	@PostMapping("/review")
	public ResponseEntity<Void> registerReview(
		@PathVariable @Positive Long workingId,
		@RequestBody @Valid ReviewRequestDto reviewRequestDto,
		@LoginUser User user) {
		WorkingDocument workingDocument = getWorkingDocumentUseCase.getWorkingDocument(workingId);
		List<Evaluation> review = reviewRequestDto.getReview();
		registerReviewUseCase.registerReview(review, user, workingDocument);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping("/review")
	public ResponseEntity<Void> updateReview(
		@PathVariable @Positive Long workingId,
		@RequestBody @Valid ReviewRequestDto reviewRequestDto,
		@LoginUser User user) {
		WorkingDocument workingDocument = getWorkingDocumentUseCase.getWorkingDocument(workingId);
		List<Evaluation> review = reviewRequestDto.getReview();
		registerReviewUseCase.updateReview(review, user, workingDocument);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@DeleteMapping("/review")
	public ResponseEntity<Void> deleteReview(@PathVariable @Positive Long workingId, @LoginUser User user) {
		WorkingDocument workingDocument = getWorkingDocumentUseCase.getWorkingDocument(workingId);
		deleteReviewUseCase.deleteReview(workingDocument, user);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
