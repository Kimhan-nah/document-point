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
			.build();
	}

}
