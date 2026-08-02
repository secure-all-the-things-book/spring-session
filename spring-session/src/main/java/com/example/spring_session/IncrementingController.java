package com.example.spring_session;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@ResponseBody
class IncrementingController {


    @GetMapping("/increment")
    Map<String, Integer> increment(HttpSession session) {
        // <.>
        var existingValue = (Integer) session.getAttribute("count");
        if (existingValue == null) {
            session.setAttribute("count", 0);
        }
        var count = (Integer) session.getAttribute("count");
        // <.>
        session.setAttribute("count", count + 1);
        return Map.of("count", count);
    }
}
