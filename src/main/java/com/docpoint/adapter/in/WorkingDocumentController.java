package com.docpoint.adapter.in;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.docpoint.adapter.in.dto.WorkingDocumentDto;
import com.docpoint.adapter.in.dto.WorkingDocumentsResponseDto;
import com.docpoint.application.port.in.GetUserWorkingDocumentsUseCase;
import com.docpoint.common.annotation.LoginUser;
import com.docpoint.common.annotation.WebAdapter;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.domain.type.DocStatusType;

import lombok.RequiredArgsConstructor;

@WebAdapter
@RequiredArgsConstructor
@Validated
@RequestMapping("/workingdocs")
public class WorkingDocumentController {
	private final GetUserWorkingDocumentsUseCase getUserWorkingDocumentsUseCase;

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
}
