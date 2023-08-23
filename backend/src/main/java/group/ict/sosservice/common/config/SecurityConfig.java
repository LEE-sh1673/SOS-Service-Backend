package group.ict.sosservice.common.config;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    @Order(1)
    @Profile("dev")
    public SecurityFilterChain h2ConsoleSecurityFilterChain(final HttpSecurity http)
        throws Exception {
        http.authorizeRequests()
            .requestMatchers(toH2Console()).permitAll();
        return http.build();
    }

    @Bean
    @Order(2)
    SecurityFilterChain authorizeRequestsSecurityFilterChain(final HttpSecurity http)
        throws Exception {
        http.authorizeHttpRequests((httpRequests) -> httpRequests
            .mvcMatchers(HttpMethod.POST, "/api/v1/auth/signup").permitAll()
            .mvcMatchers(HttpMethod.POST, "/api/v1/auth/login").permitAll()
            .mvcMatchers("/docs/**").permitAll()
            .anyRequest().authenticated()
        );
        return http.build();
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain defaultSecurityFilterChain(final HttpSecurity http)
        throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.withUsername("user")
            .password(passwordEncoder.encode("password"))
            .roles("USER")
            .build();

        UserDetails admin = User.withUsername("admin")
            .password(passwordEncoder.encode("admin"))
            .roles("USER", "ADMIN")
            .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}