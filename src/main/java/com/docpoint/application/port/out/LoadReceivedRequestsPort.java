package com.docpoint.application.port.out;

import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.docpoint.domain.model.Review;
import com.docpoint.domain.model.WorkingDocument;

/**
 * 요청 받은 DocumentReviewer 목록 조회
 */
public interface LoadReceivedRequestsPort {
	Map<WorkingDocument, Optional<Review>> loadByUserId(Long userId, Pageable pageable);
}
