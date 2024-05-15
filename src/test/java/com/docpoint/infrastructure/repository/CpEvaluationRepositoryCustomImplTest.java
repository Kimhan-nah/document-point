package com.docpoint.infrastructure.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.docpoint.annotation.DataJpaUnitTest;
import com.docpoint.infrastructure.entity.CpEvaluationJpaEntity;
import com.docpoint.infrastructure.entity.DocumentReviewerJpaEntity;
import com.docpoint.infrastructure.entity.TeamJpaEntity;
import com.docpoint.infrastructure.entity.UserJpaEntity;
import com.docpoint.infrastructure.entity.WorkingAssigneeJpaEntity;
import com.docpoint.infrastructure.entity.WorkingDocumentJpaEntity;
import com.docpoint.infrastructure.entity.WorkingJpaEntity;
import com.docpoint.util.CpEvaluationTestData;
import com.docpoint.util.DocumentReviewerTestData;
import com.docpoint.util.UserTestData;
import com.docpoint.util.WorkingDocumentTestData;
import com.docpoint.util.WorkingTestData;

import jakarta.persistence.EntityManager;

@DataJpaUnitTest
class CpEvaluationRepositoryCustomImplTest {
	@Autowired
	private EntityManager em;

	@Autowired
	private CpEvaluationRepositoryCustomImpl cpEvaluationRepositoryCustomImpl;

	private WorkingDocumentJpaEntity workingDocument;
	private DocumentReviewerJpaEntity documentReviewer1;
	private DocumentReviewerJpaEntity documentReviewer2;

	@BeforeEach
	void setUp() {
		TeamJpaEntity team = new TeamJpaEntity(null, "Test Team", false);
		UserJpaEntity writer = UserTestData.createUserJpaEntity(team);
		UserJpaEntity assignee = UserTestData.createUserJpaEntity(team);
		WorkingJpaEntity working = WorkingTestData.createWorkingJpaEntity(writer);
		WorkingAssigneeJpaEntity workingAssignee = WorkingTestData.createWorkingAssignee(working, assignee);
		UserJpaEntity partLeader = UserTestData.createUserJpaEntity(team);
		UserJpaEntity teamLeader = UserTestData.createUserJpaEntity(team);

		workingDocument = WorkingDocumentTestData.createWorkingDocumentJpaEntity(working);
		documentReviewer1 = DocumentReviewerTestData.createDocumentReviewerJpaEntity(workingDocument, partLeader);
		documentReviewer2 = DocumentReviewerTestData.createDocumentReviewerJpaEntity(workingDocument, teamLeader);

		em.persist(team);
		em.persist(writer);
		em.persist(assignee);
		em.persist(working);
		em.persist(workingAssignee);
		em.persist(partLeader);
		em.persist(teamLeader);
		em.persist(workingDocument);
		em.persist(documentReviewer1);
		em.persist(documentReviewer2);

		em.flush();
		em.clear();
	}

	@Test
	@DisplayName("2개의 CpEvaluation 조회 성공")
	void findByWorkingDocumentIdTest() {
		// Arrange
		CpEvaluationJpaEntity cpEvaluation = CpEvaluationTestData.createCpEvaluation(documentReviewer1);
		CpEvaluationJpaEntity cpEvaluation2 = CpEvaluationTestData.createCpEvaluation(documentReviewer2);

		em.persist(cpEvaluation);
		em.persist(cpEvaluation2);
		em.flush();

		// Act
		List<CpEvaluationJpaEntity> evaluations = cpEvaluationRepositoryCustomImpl.findByWorkingDocumentId(
			workingDocument.getId());

		// Assert
		assertThat(evaluations).isNotNull();
		assertThat(evaluations).hasSize(2);
	}

	@Test
	@DisplayName("WorkingDocument에 리뷰어의 CpEvaluation 조회 성공")
	void findByWorkingDocumentIdAndDocumentReviewerIdTest() {
		// Arrange
		CpEvaluationJpaEntity cpEvaluation = CpEvaluationTestData.createCpEvaluation(documentReviewer1);
		CpEvaluationJpaEntity cpEvaluation2 = CpEvaluationTestData.createCpEvaluation(documentReviewer2);

		em.persist(cpEvaluation);
		em.persist(cpEvaluation2);
		em.flush();

		// Act
		CpEvaluationJpaEntity evaluation = cpEvaluationRepositoryCustomImpl.findByWorkingDocumentIdAndDocumentReviewerId(
			workingDocument.getId(), documentReviewer1.getId()).orElse(null);

		// Assert
		assertThat(evaluation).isNotNull();
		assertThat(evaluation).isEqualTo(cpEvaluation);
	}

	@Test
	@DisplayName("WorkingDocument에 리뷰어의 CpEvaluation 없을 경우, Optional.empty() 반환")
	void cp_평가_없을_경우() {
		Optional<CpEvaluationJpaEntity> evaluation = cpEvaluationRepositoryCustomImpl.findByWorkingDocumentIdAndDocumentReviewerId(
			workingDocument.getId(), documentReviewer1.getId());

		assertThat(evaluation).isEmpty();
	}
}
