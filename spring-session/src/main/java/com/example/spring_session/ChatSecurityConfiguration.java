package com.example.spring_session;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
class ChatSecurityConfiguration {

	@Bean
	Customizer<HttpSecurity> chatHttpSecurityCustomizer() {
		return http -> http.authorizeHttpRequests(a -> a.requestMatchers("/chat").authenticated());
	}

}
