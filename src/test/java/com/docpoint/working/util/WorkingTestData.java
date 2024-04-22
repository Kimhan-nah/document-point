package com.docpoint.working.util;

import java.time.LocalDateTime;

import com.docpoint.user.domain.entity.Team;
import com.docpoint.user.util.UserTestData;
import com.docpoint.working.domain.entity.Working;
import com.docpoint.working.domain.type.WorkingCategoryType;

public class WorkingTestData {

	public static Working createWorking() {
		return Working.builder()
			.writer(UserTestData.createTeamMember(new Team("team")))
			.cp(1)
			.title("title")
			.content("content")
			.category(WorkingCategoryType.DEV)
			.dueDate(LocalDateTime.now().plusDays(1))
			.recruitDate(LocalDateTime.now())
			.build();
	}
}
