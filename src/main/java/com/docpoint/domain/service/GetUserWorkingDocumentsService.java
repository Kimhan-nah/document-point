package com.docpoint.domain.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.docpoint.application.port.in.GetUserWorkingDocumentsUseCase;
import com.docpoint.application.port.out.LoadUserWorkingDocumentsPort;
import com.docpoint.domain.model.User;
import com.docpoint.domain.model.WorkingDocument;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetUserWorkingDocumentsService implements GetUserWorkingDocumentsUseCase {
	private final LoadUserWorkingDocumentsPort loadUserWorkingDocumentsPort;

	/**
	 * 유저의 워킹 문서(WorkingDocument)를 조회한다
	 * @param user 유저 ID
	 * @param pageable 페이징 정보
	 * @return 유저의 워킹 문서(WorkingDocument) 목록
	 */
	@Override
	@Transactional(readOnly = true)
	public List<WorkingDocument> getUserWorkingDocuments(User user, Pageable pageable) {
		return loadUserWorkingDocumentsPort.loadByUser(user, pageable).getContent();
	}
}
