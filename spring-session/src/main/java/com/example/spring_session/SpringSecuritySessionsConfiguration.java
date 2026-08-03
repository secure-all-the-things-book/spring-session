package com.example.spring_session;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.session.SessionRegistry;

@Configuration
class SpringSecuritySessionsConfiguration {

	@Bean
	Customizer<HttpSecurity> securitySessionsCustomizer(SessionRegistry sessionRegistry) {
		return http -> http //
			.sessionManagement(session -> session//
				.maximumSessions(2) // <.>
				.sessionRegistry(sessionRegistry) // <.>
			); //
	}

}
