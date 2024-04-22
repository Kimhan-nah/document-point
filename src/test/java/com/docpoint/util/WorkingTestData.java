package com.docpoint.util;

import java.time.LocalDateTime;

import com.docpoint.domain.model.Team;
import com.docpoint.util.UserTestData;
import com.docpoint.domain.model.Working;
import com.docpoint.domain.type.WorkingCategoryType;

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
