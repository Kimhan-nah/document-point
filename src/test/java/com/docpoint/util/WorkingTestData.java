package com.docpoint.util;

import java.time.LocalDateTime;

import com.docpoint.domain.entity.Team;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.Working;
import com.docpoint.domain.type.WorkingCategoryType;
import com.docpoint.domain.type.WorkingStatusType;
import com.docpoint.infrastructure.entity.UserJpaEntity;
import com.docpoint.infrastructure.entity.WorkingAssigneeJpaEntity;
import com.docpoint.infrastructure.entity.WorkingJpaEntity;

public class WorkingTestData {

	public static Working createWorking() {
		return Working.builder()
			.writer(UserTestData.createTeamMember(new Team(null, "team", false)))
			.assignee(UserTestData.createTeamMember(new Team(null, "team", false)))
			.cp(1)
			.title("title")
			.content("content")
			.status(WorkingStatusType.WAITING)
			.category(WorkingCategoryType.DEV)
			.dueDate(LocalDateTime.now().plusDays(1))
			.recruitDate(LocalDateTime.now())
			.isDeleted(false)
			.build();
	}

	/**
	 * 삭제된 Working 생성
	 */
	public static Working createDeletedWorking() {
		return Working.builder()
			.writer(UserTestData.createTeamMember(new Team(null, "team", false)))
			.assignee(UserTestData.createTeamMember(new Team(null, "team", false)))
			.cp(1)
			.title("title")
			.content("content")
			.status(WorkingStatusType.DONE)
			.category(WorkingCategoryType.DEV)
			.dueDate(LocalDateTime.now().plusDays(1))
			.recruitDate(LocalDateTime.now())
			.isDeleted(true)
			.build();
	}

	/**
	 * 주어진 status를 가진 Working 생성
	 */
	public static Working createWorkingWithStatus(WorkingStatusType status) {
		return Working.builder()
			.writer(UserTestData.createTeamMember(new Team(null, "team", false)))
			.assignee(UserTestData.createTeamMember(new Team(null, "team", false)))
			.cp(1)
			.title("title")
			.content("content")
			.status(status)
			.category(WorkingCategoryType.DEV)
			.dueDate(LocalDateTime.now().plusDays(1))
			.recruitDate(LocalDateTime.now())
			.isDeleted(false)
			.build();
	}

	/**
	 * 주어진 assignee를 가진 Working 생성
	 * <p> 담당자가 지정되므로 상태는 PROCEEDING(진행중)으로 설정 </p>
	 * @param assignee 담당자
	 * @return Working
	 */
	public static Working createWorkingWithAssignee(User assignee) {
		return Working.builder()
			.writer(UserTestData.createTeamMember(new Team(null, "team", false)))
			.assignee(assignee)
			.cp(1)
			.title("title")
			.content("content")
			.status(WorkingStatusType.PROCEEDING)
			.category(WorkingCategoryType.DEV)
			.dueDate(LocalDateTime.now().plusDays(1))
			.recruitDate(LocalDateTime.now())
			.isDeleted(false)
			.build();
	}

	public static WorkingJpaEntity createWorkingJpaEntity(UserJpaEntity user) {
		return WorkingJpaEntity.builder()
			.writer(user)
			.cp(1)
			.title("title")
			.content("content")
			.status(WorkingStatusType.PROCEEDING)
			.category(WorkingCategoryType.DEV)
			.dueDate(LocalDateTime.now().plusDays(1))
			.recruitDate(LocalDateTime.now())
			.isDeleted(false)
			.build();
	}

	public static WorkingJpaEntity createWorkingJpaEntity(UserJpaEntity user, String title) {
		return WorkingJpaEntity.builder()
			.writer(user)
			.cp(1)
			.title(title)
			.content("content")
			.status(WorkingStatusType.PROCEEDING)
			.category(WorkingCategoryType.DEV)
			.dueDate(LocalDateTime.now().plusDays(1))
			.recruitDate(LocalDateTime.now())
			.isDeleted(false)
			.build();
	}

	public static WorkingAssigneeJpaEntity createWorkingAssignee(WorkingJpaEntity working, UserJpaEntity assignee) {
		return WorkingAssigneeJpaEntity.builder()
			.working(working)
			.assignee(assignee)
			.build();
	}
}
