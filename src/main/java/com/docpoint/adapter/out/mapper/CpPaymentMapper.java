package com.docpoint.adapter.out.mapper;

import com.docpoint.domain.entity.CpPayment;
import com.docpoint.infrastructure.entity.CpPaymentJpaEntity;

public class CpPaymentMapper {
	public static CpPayment mapToDomainEntity(CpPaymentJpaEntity cpPayment) {
		return CpPayment.builder()
			.id(cpPayment.getId())
			.user(cpPayment.isUserEmpty() ? null : UserMapper.mapToDomainEntity(cpPayment.getUser()))
			.cp(cpPayment.getCp())
			.build();
	}
}
