package com.example.spring_session;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
class ChatController {

	// <.>
	@GetMapping("/chat")
	String chatPage(Principal principal, Model model) {
		model.addAttribute("username", principal.getName());
		return "chat";
	}

	// <.>
	@MessageMapping("/send")
	@SendTo("/topic/public")
	ChatMessage send(@Payload ChatMessage message, Principal principal) {
		return new ChatMessage(principal.getName(), message.content());
	}
}

