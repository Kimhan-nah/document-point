package com.docpoint.infrastructure.repository;

import java.util.List;

import com.docpoint.infrastructure.entity.WorkingJpaEntity;

public interface WorkingRepositoryCustom {
	List<WorkingJpaEntity> searchUserWorking(long userId, String search);
}
