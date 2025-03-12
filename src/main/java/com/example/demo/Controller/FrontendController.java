package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontendController {

    @GetMapping("/")
    public String home() {
        return "login";  // Busca login.html en src/main/resources/templates/
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";  // Busca admin.html en src/main/resources/templates/
    }

    @GetMapping("/user")
    public String userPage() {
        return "user";  // Busca user.html en src/main/resources/templates/
    }
}
