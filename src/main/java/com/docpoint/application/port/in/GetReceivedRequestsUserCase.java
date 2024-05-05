package com.docpoint.application.port.in;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.docpoint.application.port.out.dto.WorkingDocumentWithReviewDto;
import com.docpoint.domain.entity.User;

public interface GetReceivedRequestsUserCase {
	Page<WorkingDocumentWithReviewDto> getReceivedRequests(User user, Pageable pageable);
}
