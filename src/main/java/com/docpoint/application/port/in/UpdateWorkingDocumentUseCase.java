package com.docpoint.application.port.in;

import com.docpoint.domain.model.WorkingDocument;
import com.docpoint.domain.type.DocStatusType;

/**
 * TODO adpater에서 호출하지 못하도록 접근 제어자 default로 설정
 * 		(domain.service를 application.port.in.service로 변경)
 */
public interface UpdateWorkingDocumentUseCase {
	WorkingDocument updateStatus(WorkingDocument workingDocument, DocStatusType docStatusType);
}
