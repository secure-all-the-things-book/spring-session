package com.example.spring_session;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Configuration
class ChatSecurityConfiguration {

	@Bean
	Customizer<HttpSecurity> chatHttpSecurityCustomizer() {
		return http -> http.authorizeHttpRequests(a -> a.requestMatchers("/chat").authenticated());
	}

}

@Controller
class ChatController {

	@GetMapping("/chat")
	String getChatPage(Principal principal, Model model) {
		model.addAttribute("username", principal.getName());
		return "chat";
	}

	@MessageMapping("/send")
	@SendTo("/topic/public") // <--- ADD THIS
	ChatMessage sendMessage(@Payload ChatMessage message, Principal principal) {
		return new ChatMessage(principal.getName(), message.content());
	}

}

record ChatMessage(String sender, String content) {
}