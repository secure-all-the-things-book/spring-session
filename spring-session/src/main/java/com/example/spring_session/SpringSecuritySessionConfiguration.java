package com.example.spring_session;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;

@Configuration
class SpringSecuritySessionConfiguration {

	// <.>
	@Bean
	<S extends Session> SpringSessionBackedSessionRegistry<S> sessionRegistry(
			FindByIndexNameSessionRepository<S> sessionRepository) {
		return new SpringSessionBackedSessionRegistry<>(sessionRepository);
	}

}
