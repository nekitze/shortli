package edu.nikitazubov.shortli.controller;

import edu.nikitazubov.shortli.entity.Url;
import edu.nikitazubov.shortli.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value = {"/robots.txt", "/robot.txt"})
    @ResponseBody
    public String getRobotsTxt() {
        return """
                User-agent: *
                Disallow: /
                Allow: /$
                """;
    }
}
