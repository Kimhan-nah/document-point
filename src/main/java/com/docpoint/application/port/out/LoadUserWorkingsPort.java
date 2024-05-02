package com.docpoint.application.port.out;

import java.util.List;

import com.docpoint.domain.entity.Working;
import com.docpoint.domain.type.WorkingStatusType;

public interface LoadUserWorkingsPort {
	List<Working> loadByStatusIsNotAndTitle(long userId, WorkingStatusType status, String search);
}
