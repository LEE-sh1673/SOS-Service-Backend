package group.ict.sosservice.common.annotations;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

import org.springframework.context.annotation.Profile;
import org.springframework.security.test.context.support.WithSecurityContext;

import group.ict.sosservice.common.supports.WithMockTestUserSecurityContextFactory;

@Profile("test")
@Retention(RUNTIME)
@WithSecurityContext(factory = WithMockTestUserSecurityContextFactory.class)
public @interface WithMockTestUser {

    String email() default "test-user@gmail.com";

    String password() default "1234";

    String role() default "USER";
}
