package com.docpoint.infrastructure.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.docpoint.annotation.DataJpaUnitTest;
import com.docpoint.infrastructure.entity.DocumentReviewerJpaEntity;
import com.docpoint.infrastructure.entity.ReviewJpaEntity;
import com.docpoint.infrastructure.entity.TeamJpaEntity;
import com.docpoint.infrastructure.entity.UserJpaEntity;
import com.docpoint.infrastructure.entity.WorkingAssigneeJpaEntity;
import com.docpoint.infrastructure.entity.WorkingDocumentJpaEntity;
import com.docpoint.infrastructure.entity.WorkingJpaEntity;
import com.docpoint.util.DocumentReviewerTestData;
import com.docpoint.util.ReviewTestData;
import com.docpoint.util.UserTestData;
import com.docpoint.util.WorkingDocumentTestData;
import com.docpoint.util.WorkingTestData;

import jakarta.persistence.EntityManager;

@DataJpaUnitTest
class ReviewRepositoryCustomImplTest {
	@Autowired
	private EntityManager em;

	@Autowired
	private ReviewRepositoryCustomImpl reviewRepositoryCustomImpl;

	private TeamJpaEntity team;
	private UserJpaEntity reviewer;
	private WorkingJpaEntity working;
	private WorkingDocumentJpaEntity workingDocument;
	private DocumentReviewerJpaEntity documentReviewer;

	@BeforeEach
	void setUp() {
		team = new TeamJpaEntity(null, "Test Team", false);
		UserJpaEntity writer = UserTestData.createUserJpaEntity(team);
		UserJpaEntity assignee = UserTestData.createUserJpaEntity(team);
		WorkingAssigneeJpaEntity workingAssignee = WorkingTestData.createWorkingAssignee(working, assignee);

		working = WorkingTestData.createWorkingJpaEntity(writer);
		workingDocument = WorkingDocumentTestData.createWorkingDocumentJpaEntity(working);
		reviewer = UserTestData.createUserJpaEntity(team);
		documentReviewer = DocumentReviewerTestData.createDocumentReviewerJpaEntity(workingDocument, reviewer);

		em.persist(team);
		em.persist(writer);
		em.persist(assignee);
		em.persist(workingAssignee);
		em.persist(working);
		em.persist(workingDocument);
		em.persist(reviewer);
		em.persist(documentReviewer);

		em.flush();
		em.clear();
	}

	@Nested
	@DisplayName("리뷰어의 Review가 3개(명확성, 일관성,완성도) 존재할 경우")
	class 리뷰_존재 {
		@BeforeEach
		void setUp() {
			// Review 생성
			List<ReviewJpaEntity> reviews = ReviewTestData.createReviewJpaEntity(documentReviewer);
			for (ReviewJpaEntity review : reviews) {
				em.persist(review);
			}
		}

		@Test
		@DisplayName("리뷰어의 Review 3개가 조회된다.")
		void 리뷰어의_리뷰_조회() {
			List<ReviewJpaEntity> allByWorkingDocument = reviewRepositoryCustomImpl.findAllByWorkingDocument(
				workingDocument.getId());

			assertThat(allByWorkingDocument).hasSize(3);
		}

		@Test
		@DisplayName("리뷰어의 Review 존재 여부가 true이다.")
		void 리뷰어의_리뷰_존재_여부_조회() {
			boolean existsByWorkingDocumentAndReviewer = reviewRepositoryCustomImpl.existsByWorkingDocumentAndReviewer(
				workingDocument.getId(), reviewer.getId());

			assertThat(existsByWorkingDocumentAndReviewer).isTrue();
		}

		@Test
		@DisplayName("WorkingDocument에 Review 존재 여부가 true이다.")
		void 리뷰_존재_여부_조회() {
			boolean existsByWorkingDocument = reviewRepositoryCustomImpl.existsByWorkingDocument(
				workingDocument.getId());

			assertThat(existsByWorkingDocument).isTrue();
		}
	}

	@Nested
	@DisplayName("Review가 존재하지 않을 경우")
	class 리뷰_미존재 {
		@Test
		@DisplayName("빈 리스트가 조회된다.")
		void 리뷰어의_리뷰_조회() {
			List<ReviewJpaEntity> allByWorkingDocument = reviewRepositoryCustomImpl.findAllByWorkingDocument(
				workingDocument.getId());

			assertThat(allByWorkingDocument).isEmpty();
		}

		@Test
		@DisplayName("리뷰어의 Review 존재 여부가 false이다.")
		void 리뷰어의_리뷰_존재_여부_조회() {
			boolean existsByWorkingDocumentAndReviewer = reviewRepositoryCustomImpl.existsByWorkingDocumentAndReviewer(
				workingDocument.getId(), reviewer.getId());

			assertThat(existsByWorkingDocumentAndReviewer).isFalse();
		}

		@Test
		@DisplayName("WorkingDocument에 Review 존재 여부가 false이다.")
		void 리뷰_존재_여부_조회() {
			boolean existsByWorkingDocument = reviewRepositoryCustomImpl.existsByWorkingDocument(
				workingDocument.getId());

			assertThat(existsByWorkingDocument).isFalse();
		}
	}
}
