package com.docpoint.domain.service;

import java.util.ArrayList;
import java.util.List;

import com.docpoint.application.port.in.RegisterWorkingDocumentUseCase;
import com.docpoint.application.port.out.LoadEmployeePort;
import com.docpoint.application.port.out.LoadReviewPort;
import com.docpoint.application.port.out.SaveWorkingDocumentPort;
import com.docpoint.common.annotation.UseCase;
import com.docpoint.common.exception.ErrorType;
import com.docpoint.common.exception.custom.BadRequestException;
import com.docpoint.common.exception.custom.ConflictException;
import com.docpoint.common.exception.custom.ForbiddenException;
import com.docpoint.common.exception.custom.NotFoundException;
import com.docpoint.domain.entity.DocumentReviewer;
import com.docpoint.domain.entity.Team;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.Working;
import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.domain.type.DocStatusType;
import com.docpoint.domain.type.RoleType;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
class RegisterWorkingDocumentService implements RegisterWorkingDocumentUseCase {
	private final SaveWorkingDocumentPort saveWorkingDocumentPort;
	private final LoadEmployeePort loadEmployeePort;
	private final LoadReviewPort loadReviewPort;

	/**
	 * 작업문서(WorkingDocument) 등록
	 * @param workingDocument 등록할 작업문서
	 * @param working 수행 작업
	 * @param user 작업문서를 등록할 사용자 (working의 assignee)
	 * @param reviewers 작업문서의 리뷰어 목록
	 */
	@Override
	@Transactional
	public void registerWorkingDocument(WorkingDocument workingDocument, Working working, User user,
		List<User> reviewers) {

		checkAssignee(working, user);
		checkWorkingDocumentStatus(workingDocument.getStatus());
		checkReviewers(user, reviewers);

		workingDocument.updateWorking(working);
		List<DocumentReviewer> documentReviewers = createDocumentReviewers(workingDocument, reviewers, user.getTeam());
		saveWorkingDocumentPort.save(workingDocument, documentReviewers);
	}

	/**
	 * 작업문서(WorkingDocument) 수정
	 * @param from 수정 전
	 * @param to 수정 후
	 * @param user 작업문서를 수정할 사용자
	 * @param reviewers 작업문서의 리뷰어 목록
	 * @throws ConflictException 리뷰가 존재할 경우 수정 불가능
	 */
	@Override
	@Transactional
	public void updateWorkingDocument(WorkingDocument from, WorkingDocument to, User user, List<User> reviewers) {
		checkAssignee(from.getWorking(), user);
		if (loadReviewPort.existsReview(from)) {
			throw new ConflictException(ErrorType.CONFLICT_REVIEW);
		}
		checkWorkingDocumentStatus(to.getStatus());
		checkReviewers(user, reviewers);

		WorkingDocument workingDocument = new WorkingDocument(from.getId(), from.getWorking(), to.getTitle(),
			to.getContent(), to.getStatus(), to.getDocType(), to.getLink(), to.isDeleted(), to.getRegisterDate());
		List<DocumentReviewer> documentReviewers = createDocumentReviewers(workingDocument, reviewers, user.getTeam());
		saveWorkingDocumentPort.save(workingDocument, documentReviewers);
	}

	private void checkAssignee(Working working, User user) {
		if (!working.getAssignee().equals(user)) {
			throw new ForbiddenException(ErrorType.FORBIDDEN_ASSIGNEE);
		}
	}

	/**
	 * @param user 작업문서를 등록할 사용자
	 * @param reviewers 작업문서의 리뷰어 목록
	 * @throws BadRequestException 리뷰어가 없을 경우
	 * @throws BadRequestException 본인을 지정한 경우
	 * @throws BadRequestException role이 팀 멤버가 아닌 경우
	 * @throws BadRequestException 같은 팀이 아닌 경우
	 */
	private void checkReviewers(User user, List<User> reviewers) {
		if (reviewers.isEmpty()) {
			throw new BadRequestException(ErrorType.INVALID_REVIEWER);
		}
		if (reviewers.contains(user)) {
			throw new BadRequestException(ErrorType.INVALID_REVIEWER);
		}

		for (User reviewer : reviewers) {
			if (reviewer.getRole() != RoleType.TEAM_MEMBER) {
				throw new BadRequestException(ErrorType.INVALID_REVIEWER);
			}
			if (!reviewer.getTeam().equals(user.getTeam())) {
				throw new BadRequestException(ErrorType.INVALID_REVIEWER);
			}
		}
	}

	/**
	 * 작업문서(WorkingDocument)의 리뷰어(DocumentReviewer) 목록 생성
	 * <p>팀의 파트리더와 팀 리더도 함께 리뷰어로 등록 </p>
	 * @param workingDocument 작업문서
	 * @param reviewers 작업문서의 리뷰어(TEAM_MEMBER) 목록
	 * @param team 작업문서를 등록할 유저의 팀
	 * @return DocumentReviewer 목록
	 */
	private List<DocumentReviewer> createDocumentReviewers(WorkingDocument workingDocument, List<User> reviewers,
		Team team) {
		List<DocumentReviewer> documentReviewers = new ArrayList<>();
		documentReviewers.addAll(reviewers.stream()
			.map(reviewer -> new DocumentReviewer(null, workingDocument, reviewer))
			.toList());
		documentReviewers.addAll(loadEmployeePort.loadByTeamAndRole(team, RoleType.PART_LEADER).stream()
			.map(partLeaders -> new DocumentReviewer(null, workingDocument, partLeaders))
			.toList());
		User teamLeader = loadEmployeePort.loadTeamLeaderByTeam(team).orElseThrow(NotFoundException::new);
		documentReviewers.add(new DocumentReviewer(null, workingDocument, teamLeader));
		return documentReviewers;
	}

	private void checkWorkingDocumentStatus(DocStatusType status) {
		if (status != DocStatusType.REVIEW) {
			throw new BadRequestException(ErrorType.BAD_WORKING_DOCUMENT_STATUS);
		}
	}

}
