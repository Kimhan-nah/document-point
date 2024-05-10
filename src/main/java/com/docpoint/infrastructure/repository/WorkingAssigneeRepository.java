package com.docpoint.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.docpoint.infrastructure.entity.WorkingAssigneeJpaEntity;

public interface WorkingAssigneeRepository extends JpaRepository<WorkingAssigneeJpaEntity, Long> {
	WorkingAssigneeJpaEntity findOneByWorkingId(Long workingId);
}
