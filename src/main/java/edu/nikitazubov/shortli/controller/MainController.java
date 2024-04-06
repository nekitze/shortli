package edu.nikitazubov.shortli.controller;

import edu.nikitazubov.shortli.entity.Url;
import edu.nikitazubov.shortli.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final String DOMAIN_NAME = "shortli.ru/";
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
        model.addAttribute("createdUrl", DOMAIN_NAME + createdUrl.getKey());
        return "shorten";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/{key}")
    public String shorten(@PathVariable String key) {
        Url url = urlService.getUrlByKey(key);
        if(url != null) {
            return "redirect:" + url.getFullUrl();
        } else {
            return "error";
        }
    }
}
