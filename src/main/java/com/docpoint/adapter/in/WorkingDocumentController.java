package com.docpoint.adapter.in;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.docpoint.adapter.in.dto.UpdateWorkingDocumentRequestDto;
import com.docpoint.adapter.in.dto.UserDto;
import com.docpoint.adapter.in.dto.WorkingDocumentDetailResponseDto;
import com.docpoint.adapter.in.dto.WorkingDocumentDto;
import com.docpoint.adapter.in.dto.WorkingDocumentRequestDto;
import com.docpoint.adapter.in.dto.WorkingDocumentWithReviewDto;
import com.docpoint.adapter.in.dto.WorkingDocumentsResponseDto;
import com.docpoint.adapter.in.dto.WorkingDocumentsWithReviewResponseDto;
import com.docpoint.adapter.in.dto.WorkingDto;
import com.docpoint.application.port.in.DeleteWorkingDocumentUseCase;
import com.docpoint.application.port.in.GetReceivedRequestsUserCase;
import com.docpoint.application.port.in.GetUserUseCase;
import com.docpoint.application.port.in.GetUserWorkingDocumentsUseCase;
import com.docpoint.application.port.in.GetWorkingDocumentUseCase;
import com.docpoint.application.port.in.GetWorkingsUseCase;
import com.docpoint.application.port.in.RegisterWorkingDocumentUseCase;
import com.docpoint.common.annotation.LoginUser;
import com.docpoint.common.annotation.WebAdapter;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.Working;
import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.domain.type.DocStatusType;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
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
	private final GetWorkingDocumentUseCase getWorkingDocumentUseCase;
	private final GetReceivedRequestsUserCase getReceivedRequestsUserCase;
	private final DeleteWorkingDocumentUseCase deleteWorkingDocumentUseCase;

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

	@GetMapping("{workingDocId}")
	public ResponseEntity<WorkingDocumentDetailResponseDto> getWorkingDoc(
		@PathVariable @Valid @Positive Long workingDocId) {
		WorkingDocument workingDocument = getWorkingDocumentUseCase.getWorkingDocument(workingDocId);
		WorkingDto workingDto = WorkingDto.toDto(workingDocument.getWorking());
		List<UserDto> reviewers = getWorkingDocumentUseCase.getDocumentReviewers(workingDocument).stream()
			.map(UserDto::toDto)
			.toList();

		WorkingDocumentDetailResponseDto response = WorkingDocumentDetailResponseDto
			.of(workingDocument, workingDto, reviewers);

		return ResponseEntity.ok(response);
	}

	@GetMapping("received-requests")
	public ResponseEntity<WorkingDocumentsWithReviewResponseDto> getReceivedRequests(
		@RequestParam(required = false) DocStatusType status,
		@PageableDefault Pageable pageable,
		@LoginUser User user) {
		Page<WorkingDocumentWithReviewDto> receivedRequests = getReceivedRequestsUserCase.getReceivedRequests(user,
			pageable, status);
		WorkingDocumentsWithReviewResponseDto response = WorkingDocumentsWithReviewResponseDto
			.of(receivedRequests.getContent(), receivedRequests.getTotalPages());

		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Void> createWorkingDoc(
		@RequestBody @Valid WorkingDocumentRequestDto workingDocumentRequestDto,
		@LoginUser User user) {
		WorkingDocument workingDocument = WorkingDocumentRequestDto.toWorkingDocumentEntity(workingDocumentRequestDto);
		Working working = getWorkingsUseCase.getWorkingById(workingDocumentRequestDto.getWorkingId());
		List<User> reviewers = getUserUseCase.getUsers(workingDocumentRequestDto.getReviewerIds().stream().toList());

		registerWorkingDocumentUseCase.registerWorkingDocument(workingDocument, working, user, reviewers);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping("{workingDocId}")
	public ResponseEntity<Void> updateWorkingDoc(
		@PathVariable @Valid @Positive Long workingDocId,
		@RequestBody @Valid UpdateWorkingDocumentRequestDto updateWorkingDocumentRequestDto,
		@LoginUser User user) {
		WorkingDocument from = getWorkingDocumentUseCase.getWorkingDocument(workingDocId);
		WorkingDocument to = UpdateWorkingDocumentRequestDto.
			toWorkingDocumentEntity(workingDocId, updateWorkingDocumentRequestDto);
		List<User> reviewers = getUserUseCase.getUsers(
			updateWorkingDocumentRequestDto.getReviewerIds().stream().toList());

		registerWorkingDocumentUseCase.updateWorkingDocument(from, to, user, reviewers);

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("{workingDocId}")
	public ResponseEntity<Void> deleteWorkingDoc(
		@PathVariable @Valid @Positive Long workingDocId,
		@LoginUser User user) {
		WorkingDocument workingDocument = getWorkingDocumentUseCase.getWorkingDocument(workingDocId);
		deleteWorkingDocumentUseCase.deleteWorkingDocument(workingDocument, user);

		return ResponseEntity.noContent().build();
	}
}
