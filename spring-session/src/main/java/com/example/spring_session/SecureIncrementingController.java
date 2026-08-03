package com.example.spring_session;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.Map;

@Controller
@ResponseBody
class SecureIncrementingController {

	@GetMapping("/secure-increment")
	Map<String, Object> increment(HttpSession session, Principal principal) {
		// <.>
		var existingValue = (Integer) session.getAttribute("secureCount");
		if (existingValue == null) {
			session.setAttribute("secureCount", 0);
		}
		var count = (Integer) session.getAttribute("secureCount");
		// <.>
		session.setAttribute("secureCount", count + 1);
		return Map.of("count", count, "name", principal.getName());
	}

}
