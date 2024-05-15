package com.docpoint.infrastructure.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.docpoint.annotation.DataJpaUnitTest;
import com.docpoint.domain.type.DocStatusType;
import com.docpoint.infrastructure.entity.TeamJpaEntity;
import com.docpoint.infrastructure.entity.UserJpaEntity;
import com.docpoint.infrastructure.entity.WorkingAssigneeJpaEntity;
import com.docpoint.infrastructure.entity.WorkingDocumentJpaEntity;
import com.docpoint.infrastructure.entity.WorkingJpaEntity;
import com.docpoint.util.DocumentReviewerTestData;
import com.docpoint.util.UserTestData;
import com.docpoint.util.WorkingDocumentTestData;
import com.docpoint.util.WorkingTestData;

import jakarta.persistence.EntityManager;

@DataJpaUnitTest
@DisplayName("WorkingDocument 조회 페이징 및 필터링 테스트")
class WorkingDocumentRepositoryCustomImplTest {
	@Autowired
	private EntityManager em;

	@Autowired
	private WorkingDocumentRepositoryCustomImpl workingDocumentRepositoryCustomImpl;

	private TeamJpaEntity team;
	private UserJpaEntity user1;
	private UserJpaEntity user2;
	private WorkingJpaEntity working1;
	private WorkingJpaEntity working2;
	private WorkingDocumentJpaEntity workingDocument1;
	private WorkingDocumentJpaEntity workingDocument2;

	@BeforeEach
	void setUp() {
		team = new TeamJpaEntity(null, "Test Team", false);
		user1 = UserTestData.createUserJpaEntity(team);
		user2 = UserTestData.createUserJpaEntity(team);
		// user2가 작성하고, user1이 담당자인 Working
		working1 = WorkingTestData.createWorkingJpaEntity(user2);
		// user1이 작성하고, user2가 담당자인 Working
		working2 = WorkingTestData.createWorkingJpaEntity(user1);
		WorkingAssigneeJpaEntity assignee1 = WorkingTestData.createWorkingAssignee(working1, user1);
		WorkingAssigneeJpaEntity assignee2 = WorkingTestData.createWorkingAssignee(working2, user2);
		workingDocument1 = WorkingDocumentTestData.createWorkingDocumentJpaEntity(working1);
		workingDocument2 = WorkingDocumentTestData.createWorkingDocumentJpaEntity(working2);

		em.persist(team);
		em.persist(user1);
		em.persist(user2);
		em.persist(working1);
		em.persist(working2);
		em.persist(assignee1);
		em.persist(assignee2);
		em.persist(workingDocument1);
		em.persist(workingDocument2);

		em.flush();
		em.clear();
	}

	@Test
	@DisplayName("내 문서 목록 조회")
	void 내_문서_목록_조회() {
		// given
		long userId = user1.getId();
		PageRequest pageable = PageRequest.of(0, 10);
		DocStatusType status = workingDocument1.getStatus();

		// when
		Page<WorkingDocumentJpaEntity> result = workingDocumentRepositoryCustomImpl.findByAssigneeId(userId, pageable,
			status);

		// then
		assertThat(result).isNotNull();
		assertThat(result.getContent()).hasSize(1);
		assertThat(result.getContent().get(0).getId()).isEqualTo(workingDocument1.getId());
	}

	@Test
	@DisplayName("요청 받은 15개의 WorkingDocument 중 10개 조회")
	void 요청_받은_WorkingDocument_목록_조회() {
		// given
		UserJpaEntity reviewer = UserTestData.createUserJpaEntity(team);
		em.persist(reviewer);
		IntStream.range(0, 15).forEach(i -> {
			WorkingDocumentJpaEntity workingDocument = WorkingDocumentTestData.createWorkingDocumentJpaEntity(working1);
			em.persist(workingDocument);
			em.persist(DocumentReviewerTestData.createDocumentReviewerJpaEntity(workingDocument, reviewer));
		});
		PageRequest pageable = PageRequest.of(0, 10);
		DocStatusType status = null;

		// when
		Page<WorkingDocumentJpaEntity> result = workingDocumentRepositoryCustomImpl.findByReviewerId(reviewer.getId(),
			pageable, status);

		// then
		assertThat(result).isNotNull();
		assertThat(result.getContent()).hasSize(10);
	}

	@Test
	@DisplayName("요청 받은 10개의 WorkingDocument에서 REVIEW 상태를 제외한 5개 조회")
	void 요청_받은_WorkingDocument에서_특정_상태를_제외한_목록_조회() {
		// given
		UserJpaEntity reviewer = UserTestData.createUserJpaEntity(team);
		IntStream.range(0, 5).forEach(i -> {
			WorkingDocumentJpaEntity workingDocument = WorkingDocumentTestData.createWorkingDocumentJpaEntity(
				working1, DocStatusType.REVIEW);
			em.persist(workingDocument);
			em.persist(DocumentReviewerTestData.createDocumentReviewerJpaEntity(workingDocument, reviewer));
		});
		IntStream.range(0, 5).forEach(i -> {
			WorkingDocumentJpaEntity workingDocument = WorkingDocumentTestData.createWorkingDocumentJpaEntity(
				working1, DocStatusType.APPROVED);
			em.persist(workingDocument);
			em.persist(DocumentReviewerTestData.createDocumentReviewerJpaEntity(workingDocument, reviewer));
		});
		em.persist(reviewer);
		em.flush();
		em.clear();

		PageRequest pageable = PageRequest.of(0, 10);
		DocStatusType excludeStatus = DocStatusType.REVIEW;
		DocStatusType status = null;

		// when
		Page<WorkingDocumentJpaEntity> result = workingDocumentRepositoryCustomImpl.findByUserIdExcludeStatus(
			reviewer.getId(), pageable, excludeStatus, status);

		// then
		assertThat(result).isNotNull();
		assertThat(result.getContent()).hasSize(5);
		assertThat(result.getContent()).allMatch(w -> w.getStatus() != DocStatusType.REVIEW);
	}

	@Test
	@DisplayName("팀 전체 WorkingDocument 목록 조회")
	void 팀_전체_WorkingDocument_목록_조회() {
		// given
		IntStream.range(0, 15).forEach(i -> {
			WorkingDocumentJpaEntity workingDocument = WorkingDocumentTestData.createWorkingDocumentJpaEntity(working1);
			em.persist(workingDocument);
		});
		PageRequest pageable = PageRequest.of(0, 10);

		// when
		Page<WorkingDocumentJpaEntity> result = workingDocumentRepositoryCustomImpl.findByTeamId(team.getId(), pageable,
			null);

		// then
		assertThat(result).isNotNull();
		assertThat(result.getContent()).hasSize(10);

	}

}
