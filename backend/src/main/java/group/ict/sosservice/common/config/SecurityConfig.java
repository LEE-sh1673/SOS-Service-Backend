package group.ict.sosservice.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.session.security.web.authentication.SpringSessionRememberMeServices;

import com.fasterxml.jackson.databind.ObjectMapper;

import group.ict.sosservice.authentication.filter.EmailPasswordAuthFilter;
import group.ict.sosservice.authentication.handler.LoginFailHandler;
import group.ict.sosservice.authentication.handler.LoginSuccessHandler;
import group.ict.sosservice.authentication.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final ObjectMapper objectMapper;

    private final CustomUserDetailsService userDetailsService;

    @Value("${security.authentication.validity-seconds}")
    private long validityInSeconds;

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(final HttpSecurity http)
        throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.authorizeHttpRequests((httpRequests) -> httpRequests
            .antMatchers("/docs/**").permitAll()
            .antMatchers("/h2-console/**").permitAll()
            .antMatchers(HttpMethod.POST, "/api/v1/auth/signup").permitAll()
            .antMatchers(HttpMethod.POST, "/api/v1/auth/login").permitAll()
            .anyRequest().authenticated()
        );
        http.addFilterBefore(
            emailPasswordAuthFilter(),
            UsernamePasswordAuthenticationFilter.class
        );
        return http.build();
    }

    @Bean
    public EmailPasswordAuthFilter emailPasswordAuthFilter() {
        EmailPasswordAuthFilter filter = new EmailPasswordAuthFilter("/api/v1/auth/login", objectMapper);

        filter.setRememberMeServices(rememberMeServices());
        filter.setAuthenticationManager(authenticationManager());

        filter.setAuthenticationFailureHandler(new LoginFailHandler(objectMapper));
        filter.setAuthenticationSuccessHandler(new LoginSuccessHandler(objectMapper));
        filter.setSecurityContextRepository(new HttpSessionSecurityContextRepository());
        return filter;
    }

    @Bean
    public RememberMeServices rememberMeServices() {
        SpringSessionRememberMeServices rememberMeServices = new SpringSessionRememberMeServices();
        rememberMeServices.setValiditySeconds(Math.toIntExact(validityInSeconds));
        rememberMeServices.setRememberMeParameterName("remember_me");
        rememberMeServices.setAlwaysRemember(true);
        return rememberMeServices;
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(provider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
