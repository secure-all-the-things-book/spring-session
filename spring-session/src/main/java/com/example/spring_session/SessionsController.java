package com.example.spring_session;

import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.Map;

@Controller
@ResponseBody
class SessionsController {

	private final SessionRegistry registry;

	SessionsController(SessionRegistry registry) {
		this.registry = registry;
	}

	@GetMapping("/sessions")
	Map<String, Object> sessions(Principal principal) {
		return Map.of("sessions", this.registry.getAllSessions(principal, false));
	}

}
