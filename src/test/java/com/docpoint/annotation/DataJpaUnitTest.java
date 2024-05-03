package com.docpoint.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import com.docpoint.infrastructure.config.JpaConfig;
import com.docpoint.infrastructure.config.QueryDslConfig;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@DataJpaTest
@Import({JpaConfig.class, QueryDslConfig.class})
public @interface DataJpaUnitTest {

}
