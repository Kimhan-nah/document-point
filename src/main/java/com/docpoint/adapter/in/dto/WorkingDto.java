package com.docpoint.adapter.in.dto;

import com.docpoint.domain.entity.Working;
import com.docpoint.domain.type.WorkingStatusType;

import lombok.Builder;
import lombok.Getter;

@Getter
public class WorkingDto {
	private Long id;
	private String title;
	private String requesterName;
	private String assigneeName;
	private WorkingStatusType status;

	@Builder
	public WorkingDto(Long id, String title, String requesterName, String assigneeName, WorkingStatusType status) {
		this.id = id;
		this.title = title;
		this.requesterName = requesterName;
		this.assigneeName = assigneeName;
		this.status = status;
	}

	public static WorkingDto toDto(Working working) {
		return WorkingDto.builder()
			.id(working.getId())
			.title(working.getTitle())
			.requesterName(working.getWriter().getName())
			.assigneeName(working.getAssignee().getName())
			.status(working.getStatus())
			.build();
	}
}
