package edu.nikitazubov.shortli.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    @GetMapping
    public String mainPage() {
        return "main";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @PostMapping
    public String shorten() {return "login";}

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
