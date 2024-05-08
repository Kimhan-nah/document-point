package com.docpoint.adapter.in.dto;

import com.docpoint.domain.entity.Working;
import com.docpoint.domain.type.WorkingStatusType;

import lombok.Builder;
import lombok.Getter;

@Getter
public class WorkingDto {
	private Long workingId;
	private String title;
	private String requesterName;
	private WorkingStatusType status;

	@Builder
	public WorkingDto(Long workingId, String title, String requesterName, WorkingStatusType status) {
		this.workingId = workingId;
		this.title = title;
		this.requesterName = requesterName;
		this.status = status;
	}

	public static WorkingDto toDto(Working working) {
		return WorkingDto.builder()
			.workingId(working.getId())
			.title(working.getTitle())
			.requesterName(working.getWriter().getName())
			.status(working.getStatus())
			.build();
	}
}
