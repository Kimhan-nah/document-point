package com.docpoint.domain.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.docpoint.application.port.in.GetUserWorkingDocumentsUseCase;
import com.docpoint.application.port.out.LoadUserPort;
import com.docpoint.application.port.out.LoadUserWorkingDocumentsPort;
import com.docpoint.common.exception.custom.NotFoundException;
import com.docpoint.domain.model.WorkingDocument;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetUserWorkingDocumentsService implements GetUserWorkingDocumentsUseCase {
	private final LoadUserPort loadUserPort;
	private final LoadUserWorkingDocumentsPort loadUserWorkingDocumentsPort;

	/**
	 * 유저의 워킹 문서(WorkingDocument)를 조회한다
	 * @param userId 유저 ID
	 * @param pageable 페이징 정보
	 * @return 유저의 워킹 문서(WorkingDocument) 목록
	 */
	@Override
	@Transactional(readOnly = true)
	public List<WorkingDocument> getUserWorkingDocuments(Long userId, Pageable pageable) {
		loadUserPort.loadById(userId).orElseThrow(() -> new NotFoundException("존재하지 않는 유저입니다."));
		return loadUserWorkingDocumentsPort.loadByUserId(userId, pageable).getContent();
	}
}
