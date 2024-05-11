package com.docpoint.adapter.out;

import com.docpoint.adapter.out.mapper.WorkingDocumentMapper;
import com.docpoint.application.port.out.SaveWorkingDocumentPort;
import com.docpoint.common.annotation.PersistenceAdapter;
import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.infrastructure.entity.WorkingDocumentJpaEntity;
import com.docpoint.infrastructure.repository.WorkingDocumentRepository;

import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class SaveWorkingDocumentAdapter implements SaveWorkingDocumentPort {
	private final WorkingDocumentRepository workingDocumentRepository;

	@Override
	public void save(WorkingDocument workingDocument) {
		WorkingDocumentJpaEntity workingDocumentJpaEntity = WorkingDocumentMapper.mapToJpaEntity(workingDocument);
		WorkingDocumentJpaEntity save = workingDocumentRepository.save(workingDocumentJpaEntity);
	}
}
