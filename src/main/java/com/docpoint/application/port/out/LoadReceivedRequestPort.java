package com.docpoint.application.port.out;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.domain.type.DocStatusType;

/**
 * 요청 받은 DocumentReviewer 목록 조회
 */
public interface LoadReceivedRequestPort {
	Page<WorkingDocument> loadByUser(User user, Pageable pageable, DocStatusType status);

	Page<WorkingDocument> loadByUserWithExcludeStatus(User user, Pageable pageable,
		DocStatusType excludeStatus, DocStatusType status);
}
