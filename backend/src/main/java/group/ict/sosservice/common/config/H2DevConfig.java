package group.ict.sosservice.common.config;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Profile("dev")
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class H2DevConfig {

    @Bean
    public SecurityFilterChain h2ConsoleSecurityFilterChain(final HttpSecurity http)
        throws Exception {
        http.authorizeRequests()
            .requestMatchers(toH2Console()).permitAll();
        return http.build();
    }
}
