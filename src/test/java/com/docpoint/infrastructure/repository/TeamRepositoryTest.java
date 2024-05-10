package com.docpoint.infrastructure.repository;

import static com.docpoint.infrastructure.entity.QDocumentReviewerJpaEntity.*;
import static com.docpoint.infrastructure.entity.QReviewJpaEntity.*;
import static com.docpoint.infrastructure.entity.QUserJpaEntity.*;
import static com.docpoint.infrastructure.entity.QWorkingDocumentJpaEntity.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.docpoint.annotation.DataJpaUnitTest;
import com.docpoint.infrastructure.entity.QDocumentReviewerJpaEntity;
import com.docpoint.infrastructure.entity.QReviewJpaEntity;
import com.docpoint.infrastructure.entity.QUserJpaEntity;
import com.docpoint.infrastructure.entity.QWorkingDocumentJpaEntity;
import com.docpoint.infrastructure.entity.TeamJpaEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;

@DataJpaUnitTest
@DisplayName("TeamRepository 테스트")
class TeamRepositoryTest {

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private JPAQueryFactory jpaQueryFactory;

	@Test
	@DisplayName("팀 저장 테스트")
	void testTeamRepository() {
		// given
		TeamJpaEntity team = new TeamJpaEntity("team1", false);
		teamRepository.save(team);

		// when
		int numberOfTeams = teamRepository.findAll().size();

		// then
		assertEquals(1, numberOfTeams);
	}

	@Test
	@DisplayName("팀 조회 테스트")
	void testFindTeam() {
		// given
		TeamJpaEntity team = new TeamJpaEntity("team1", false);
		teamRepository.save(team);

		// when
		TeamJpaEntity foundTeam = teamRepository.findById(team.getId()).orElse(null);

		// then
		assertNotNull(foundTeam);
		assertEquals(team.getId(), foundTeam.getId());
		assertEquals(team.getName(), foundTeam.getName());
		assertEquals(team.getIsDeleted(), foundTeam.getIsDeleted());
	}

	@Test
	void test() {
		QWorkingDocumentJpaEntity workingDocument = workingDocumentJpaEntity;
		QReviewJpaEntity review = reviewJpaEntity;
		QDocumentReviewerJpaEntity documentReviewer = documentReviewerJpaEntity;
		QUserJpaEntity user = userJpaEntity;

		jpaQueryFactory
			.selectOne()
			.from(documentReviewer)
			.where(documentReviewer.reviewer.id.eq(1L),
				documentReviewer.workingDocument.id.eq(1L))
			.fetchFirst();

		// List<Long> fetch = jpaQueryFactory.select(user.count())
		// 	.from(user)
		// 	.fetch();
		//
		// System.out.println(fetch.size());
		// System.out.println(fetch.get(0));

		// List<WorkingDocumentWithReviewDto> content = jpaQueryFactory
		// 	.select(new QWorkingDocumentWithReviewDto(
		// 		workingDocument.id,
		// 		workingDocument.title,
		// 		workingDocument.content,
		// 		workingDocument.status,
		// 		workingDocument.type,
		// 		workingDocument.link,
		// 		workingDocument.isDeleted,
		// 		review.isDeleted
		// 	))
		// 	.from(review)
		// 	.rightJoin(review.documentReviewer, documentReviewer)
		// 	.on(documentReviewer.reviewer.id.eq(1L))
		// 	.join(documentReviewer.workingDocument, workingDocument)
		// 	.orderBy(workingDocument.createdAt.desc())
		// 	.fetch();
	}
}
