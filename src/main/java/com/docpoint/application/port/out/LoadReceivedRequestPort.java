package com.docpoint.application.port.out;

import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.docpoint.domain.entity.Review;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.entity.WorkingDocument;

/**
 * 요청 받은 DocumentReviewer 목록 조회
 */
public interface LoadReceivedRequestPort {
	Map<WorkingDocument, Optional<Review>> loadByUser(User user, Pageable pageable);
}
