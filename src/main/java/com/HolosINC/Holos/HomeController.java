package com.HolosINC.Holos;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("mensaje", "Hola desde Spring Boot con JSP");
        return "index"; // Buscará en /WEB-INF/views/index.jsp
    }
}