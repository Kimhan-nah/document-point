package com.docpoint.domain.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.docpoint.application.port.in.GetAllWorkingDocumentsUseCase;
import com.docpoint.application.port.out.LoadTeamPort;
import com.docpoint.application.port.out.LoadWorkingDocumentsPort;
import com.docpoint.common.exception.custom.BadRequestException;
import com.docpoint.common.exception.custom.NotFoundException;
import com.docpoint.domain.model.Team;
import com.docpoint.domain.model.WorkingDocument;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetAllWorkingDocumentsService implements GetAllWorkingDocumentsUseCase {

	private final LoadWorkingDocumentsPort loadWorkingDocumentsPort;

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
	public List<WorkingDocument> getAllWorkingDocumentsByTeamId(Long teamId, Pageable pageable) {
		Team team = loadTeamPort.loadById(teamId)
			.orElseThrow(() -> new NotFoundException("존재하지 않는 팀입니다."));
		if (team.isDeleted()) {
			throw new BadRequestException("삭제된 팀입니다.");
		}
		return loadWorkingDocumentsPort.loadByTeamId(teamId, pageable);
	}
}
