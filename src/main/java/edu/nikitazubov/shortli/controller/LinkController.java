package edu.nikitazubov.shortli.controller;

import edu.nikitazubov.shortli.entity.Url;
import edu.nikitazubov.shortli.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class LinkController {
    @Value("${shortli.domain.name}")
    private String DOMAIN_NAME;
    private final UrlService urlService;

    @PostMapping("/shorten")
    public String shorten(@RequestParam(value = "fullUrl") String fullUrl, Model model) {
        Url createdUrl = urlService.addNewUrl(fullUrl);
        model.addAttribute("createdUrl", DOMAIN_NAME + "/" + createdUrl.getKey());
        return "shorten";
    }

    @GetMapping("/{key}")
    public String shorten(@PathVariable String key) {
        Url url = urlService.visitUrl(key);
        if (url != null) {
            return "redirect:" + url.getFullUrl();
        } else {
            return "error";
        }
    }
}
