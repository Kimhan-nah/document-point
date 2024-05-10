package com.docpoint.application.port.in;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.docpoint.adapter.in.dto.WorkingDocumentWithReviewDto;
import com.docpoint.domain.entity.User;
import com.docpoint.domain.type.DocStatusType;

public interface GetReceivedRequestsUserCase {
	Page<WorkingDocumentWithReviewDto> getReceivedRequests(User user, Pageable pageable, DocStatusType status);
}
