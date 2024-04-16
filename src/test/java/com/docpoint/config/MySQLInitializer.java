package com.docpoint.config;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.MySQLContainer;

public class MySQLInitializer implements
	ApplicationContextInitializer<ConfigurableApplicationContext> {
	private static final MySQLContainer<?> MYSQL_CONTAINER =
		new MySQLContainer<>("mysql:8.0");

	static {
		MYSQL_CONTAINER.start();
	}

	@Override
	public void initialize(ConfigurableApplicationContext applicationContext) {
		TestPropertyValues.of(
			"spring.datasource.url=" + MYSQL_CONTAINER.getJdbcUrl(),
			"spring.datasource.username=" + MYSQL_CONTAINER.getUsername(),
			"spring.datasource.password=" + MYSQL_CONTAINER.getPassword()
		).applyTo(applicationContext.getEnvironment());
	}
}
