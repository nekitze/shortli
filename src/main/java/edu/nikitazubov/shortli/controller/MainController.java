package edu.nikitazubov.shortli.controller;

import edu.nikitazubov.shortli.entity.Url;
import edu.nikitazubov.shortli.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UrlService urlService;

    @GetMapping
    public String mainPage() {
        return "main";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<Url> userUrls = urlService.getAllUrls();
        model.addAttribute("urlList", userUrls);
        return "dashboard";
    }

    @PostMapping("/shorten")
    public String shorten(@RequestParam(value = "fullUrl", required = true) String fullUrl, Model model) {
        Url createdUrl = urlService.addNewUrl(fullUrl);
        model.addAttribute("createdUrl", createdUrl.getShortUrl());
        return "shorten";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
