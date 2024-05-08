package com.docpoint.adapter.out.mapper;

import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.infrastructure.entity.WorkingDocumentJpaEntity;

public class WorkingDocumentMapper {
	public static WorkingDocument mapToDomainEntity(WorkingDocumentJpaEntity workingDocument) {
		return WorkingDocument.builder()
			.id(workingDocument.getId())
			.working(
				workingDocument.isWorkingEmpty() ? null : WorkingMapper.mapToDomainEntity(workingDocument.getWorking()))
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
