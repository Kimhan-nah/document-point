package com.docpoint.application.port.in;

import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.docpoint.domain.model.Review;
import com.docpoint.domain.model.User;
import com.docpoint.domain.model.WorkingDocument;

public interface GetReceivedRequestsUserCase {
	Map<WorkingDocument, Optional<Review>> getReceivedRequests(User user, Pageable pageable);
}
