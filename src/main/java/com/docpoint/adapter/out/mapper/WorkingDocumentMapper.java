package com.docpoint.adapter.out.mapper;

import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.infrastructure.entity.WorkingDocumentJpaEntity;
import com.docpoint.infrastructure.entity.WorkingJpaEntity;

public class WorkingDocumentMapper {
	public static WorkingDocument mapToDomainEntity(WorkingDocumentJpaEntity workingDocument) {
		WorkingJpaEntity working = workingDocument.getWorking();
		return WorkingDocument.builder()
			.id(workingDocument.getId())
			.working(workingDocument.isWorkingEmpty() ? null :
				WorkingMapper.mapToDomainEntityWithAssignee(working, working.getAssignee()))
			.title(workingDocument.getTitle())
			.content(workingDocument.getContent())
			.status(workingDocument.getStatus())
			.docType(workingDocument.getType())
			.link(workingDocument.getLink())
			.isDeleted(workingDocument.getIsDeleted())
			.registerDate(workingDocument.getCreatedAt().toLocalDate())
			.build();
	}

	public static WorkingDocumentJpaEntity mapToJpaEntity(WorkingDocument workingDocument) {
		return WorkingDocumentJpaEntity.builder()
			.id(workingDocument.getId())
			.working(
				workingDocument.isWorkingEmpty() ? null : WorkingMapper.mapToJpaEntity(workingDocument.getWorking()))
			.title(workingDocument.getTitle())
			.content(workingDocument.getContent())
			.status(workingDocument.getStatus())
			.type(workingDocument.getDocType())
			.link(workingDocument.getLink())
			.isDeleted(workingDocument.isDeleted())
			.build();
	}
}
