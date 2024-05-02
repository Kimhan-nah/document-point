package com.docpoint.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.docpoint.infrastructure.entity.CpPaymentJpaEntity;

public interface CpPaymentRepository extends JpaRepository<CpPaymentJpaEntity, Long> {
}
