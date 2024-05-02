package com.docpoint.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.docpoint.infrastructure.entity.WorkingJpaEntity;

public interface WorkingRepository extends JpaRepository<WorkingJpaEntity, Long> {
}
