package com.docpoint.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.docpoint.application.port.in.GetAllWorkingDocumentsUseCase;
import com.docpoint.application.port.out.LoadTeamPort;
import com.docpoint.application.port.out.LoadWorkingDocumentPort;
import com.docpoint.common.annotation.UseCase;
import com.docpoint.common.exception.ErrorType;
import com.docpoint.common.exception.custom.BadRequestException;
import com.docpoint.common.exception.custom.NotFoundException;
import com.docpoint.domain.entity.Team;
import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.domain.type.DocStatusType;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
class GetAllWorkingDocumentsService implements GetAllWorkingDocumentsUseCase {

	private final LoadWorkingDocumentPort loadWorkingDocumentPort;

	private final LoadTeamPort loadTeamPort;

	/**
	 * @param teamId WorkingDocument 조회할 팀 ID
	 * @param pageable 페이징 정보
	 * @return 팀의 전체 워킹 문서(WorkingDocument) 목록
	 * @throws NotFoundException 존재하지 않는 팀인 경우
	 * @throws BadRequestException 삭제된 팀인 경우
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<WorkingDocument> getAllWorkingDocumentsByTeamId(long teamId, Pageable pageable, DocStatusType status) {
		Team team = loadTeamPort.load(teamId)
			.orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_TEAM));
		if (team.isDeleted()) {
			throw new BadRequestException(ErrorType.DELETED_TEAM);
		}
		return loadWorkingDocumentPort.loadByTeamId(teamId, pageable, status);
	}
}
