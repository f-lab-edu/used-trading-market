package org.flab.hyunsb.framework.repository.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.flab.hyunsb.framework.persistence.config.PersistenceConfig;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@DataJpaTest
@Import(PersistenceConfig.class)
@ContextConfiguration(classes = PersistenceConfig.class)
public @interface RepositoryTest {

}
