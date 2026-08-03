package com.example.spring_session;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
class SecurityConfiguration {

	// <.>
	@Bean
	PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	// <.>
	@Bean
	InMemoryUserDetailsManager userDetailsManager(PasswordEncoder pw) {
		var josh = User //
			.withUsername("josh@joshlong.com") //
			.password(pw.encode("pw")) //
			.roles("USER") //
			.build();
		var rob = User //
			.withUsername("rob@springsecurity.site") //
			.password(pw.encode("pw"))//
			.roles("USER", "ADMIN")//
			.build();
		return new InMemoryUserDetailsManager(rob, josh);
	}

	// <.>
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity security) {
		return security.formLogin(Customizer.withDefaults())//
			.authorizeHttpRequests(a -> a //
				.requestMatchers("/sessions", "/secure-increment")
				.authenticated() //
				.anyRequest()
				.permitAll()//
			)
			.build();
	}

}
