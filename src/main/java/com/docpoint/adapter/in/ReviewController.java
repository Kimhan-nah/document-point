package com.docpoint.adapter.in;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.docpoint.adapter.in.dto.ReviewResponseDto;
import com.docpoint.adapter.in.dto.UserDto;
import com.docpoint.application.port.in.GetReviewsUseCase;
import com.docpoint.application.port.in.GetWorkingDocumentUseCase;
import com.docpoint.common.annotation.LoginUser;
import com.docpoint.common.annotation.WebAdapter;
import com.docpoint.domain.entity.Evaluation;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@WebAdapter
@RequiredArgsConstructor
@Validated
@RequestMapping("/workingdocs/{workingId}")
public class ReviewController {
	private final GetReviewsUseCase getReviewsUseCase;
	private final GetWorkingDocumentUseCase getWorkingDocumentUseCase;

	@GetMapping("/review")
	public ResponseEntity<ReviewResponseDto> getReview(@PathVariable @Positive Long workingId, @LoginUser User user) {
		WorkingDocument workingDocument = getWorkingDocumentUseCase.getWorkingDocument(workingId);
		UserDto reviewer = UserDto.toDto(user);
		List<Evaluation> review = getReviewsUseCase.getReview(workingDocument, user);

		return ResponseEntity.ok(ReviewResponseDto.of(reviewer, review));
	}
}
