package com.docpoint.util;

import java.time.LocalDateTime;

import com.docpoint.domain.model.Team;
import com.docpoint.domain.model.Working;
import com.docpoint.domain.type.WorkingCategoryType;
import com.docpoint.domain.type.WorkingStatusType;

public class WorkingTestData {

	public static Working createWorking() {
		return Working.builder()
			.writer(UserTestData.createTeamMember(new Team("team", false)))
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
			.writer(UserTestData.createTeamMember(new Team("team", false)))
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
			.writer(UserTestData.createTeamMember(new Team("team", false)))
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

}
