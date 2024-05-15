package com.docpoint.infrastructure.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.docpoint.annotation.DataJpaUnitTest;
import com.docpoint.infrastructure.entity.TeamJpaEntity;
import com.docpoint.infrastructure.entity.UserJpaEntity;
import com.docpoint.infrastructure.entity.WorkingAssigneeJpaEntity;
import com.docpoint.infrastructure.entity.WorkingJpaEntity;
import com.docpoint.util.UserTestData;
import com.docpoint.util.WorkingTestData;

import jakarta.persistence.EntityManager;

@DataJpaUnitTest
@DisplayName("WorkingRepositoryCustomImpl 테스트")
class WorkingRepositoryCustomImplTest {
	private final String title = "working title";

	@Autowired
	private WorkingRepositoryCustomImpl workingRepositoryCustom;

	@Autowired
	private EntityManager entityManager;

	private WorkingJpaEntity working1;

	private WorkingJpaEntity working2;
	private UserJpaEntity user1;

	@BeforeEach
	void setUp() {
		// 테스트 데이터 준비
		TeamJpaEntity team = new TeamJpaEntity(null, "Test Team", false);
		user1 = UserTestData.createUserJpaEntity(team);
		UserJpaEntity user2 = UserTestData.createUserJpaEntity(team);
		working1 = WorkingTestData.createWorkingJpaEntity(user2, title);
		working2 = WorkingTestData.createWorkingJpaEntity(user1, title);
		WorkingAssigneeJpaEntity assignee1 = WorkingTestData.createWorkingAssignee(working1, user1);
		WorkingAssigneeJpaEntity assignee2 = WorkingTestData.createWorkingAssignee(working2, user2);

		entityManager.persist(team);
		entityManager.persist(user1);
		entityManager.persist(user2);
		entityManager.persist(working1);
		entityManager.persist(working2);
		entityManager.persist(assignee1);
		entityManager.persist(assignee2);

		entityManager.flush();
		entityManager.clear();
	}

	@Test
	@DisplayName("동일한 제목의 Working 2개중 유저의 Working 1개를 조회한다.")
	void searchUserWorking() {
		// 테스트 실행
		String search = title;

		List<WorkingJpaEntity> results = workingRepositoryCustom.searchUserWorking(user1.getId(), search);

		// 결과 검증
		assertThat(results).hasSize(1);
		assertThat(results.get(0).getTitle()).isEqualTo(title);
	}
}
