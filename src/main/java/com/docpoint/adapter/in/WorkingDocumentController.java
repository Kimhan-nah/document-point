package com.docpoint.adapter.in;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.docpoint.adapter.in.dto.WorkingDocumentDto;
import com.docpoint.adapter.in.dto.WorkingDocumentRequestDto;
import com.docpoint.adapter.in.dto.WorkingDocumentsResponseDto;
import com.docpoint.application.port.in.GetUserUseCase;
import com.docpoint.application.port.in.GetUserWorkingDocumentsUseCase;
import com.docpoint.application.port.in.GetWorkingsUseCase;
import com.docpoint.application.port.in.RegisterWorkingDocumentUseCase;
import com.docpoint.common.annotation.LoginUser;
import com.docpoint.common.annotation.WebAdapter;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.Working;
import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.domain.type.DocStatusType;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@WebAdapter
@RequiredArgsConstructor
@Validated
@RequestMapping("/workingdocs")
public class WorkingDocumentController {
	private final GetUserWorkingDocumentsUseCase getUserWorkingDocumentsUseCase;
	private final RegisterWorkingDocumentUseCase registerWorkingDocumentUseCase;
	private final GetWorkingsUseCase getWorkingsUseCase;
	private final GetUserUseCase getUserUseCase;

	@GetMapping
	public ResponseEntity<WorkingDocumentsResponseDto> getWorkingDocs(
		@RequestParam(required = false) DocStatusType status,
		@PageableDefault Pageable pageable,
		@LoginUser User user) {
		Page<WorkingDocument> userWorkingDocuments = getUserWorkingDocumentsUseCase.getUserWorkingDocuments(user,
			pageable, status);

		return ResponseEntity.ok(
			WorkingDocumentsResponseDto.of(
				userWorkingDocuments.map(WorkingDocumentDto::toDto).toList(),
				userWorkingDocuments.getTotalPages()
			)
		);
	}

	@PostMapping
	public ResponseEntity<Void> createWorkingDoc(
		@RequestBody @Valid WorkingDocumentRequestDto workingDocumentRequestDto,
		@LoginUser User user) {
		WorkingDocument workingDocument = WorkingDocumentRequestDto.toWorkingDocumentEntity(workingDocumentRequestDto);
		Working working = getWorkingsUseCase.getWorkingById(workingDocumentRequestDto.getWorkingId());
		List<User> reviewers = getUserUseCase.getUsers(workingDocumentRequestDto.getReviewerIds());

		registerWorkingDocumentUseCase.registerWorkingDocument(workingDocument, working, user, reviewers);

		return ResponseEntity.ok().build();
	}
}
