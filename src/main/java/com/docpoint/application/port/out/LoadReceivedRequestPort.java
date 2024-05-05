package com.docpoint.application.port.out;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.docpoint.application.port.out.dto.WorkingDocumentWithReviewDto;
import com.docpoint.domain.entity.User;

/**
 * 요청 받은 DocumentReviewer 목록 조회
 */
public interface LoadReceivedRequestPort {
	Page<WorkingDocumentWithReviewDto> loadByUser(User user, Pageable pageable);
}
