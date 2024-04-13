package edu.nikitazubov.shortli.controller;

import edu.nikitazubov.shortli.entity.Url;
import edu.nikitazubov.shortli.entity.User;
import edu.nikitazubov.shortli.service.UrlService;
import edu.nikitazubov.shortli.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final UrlService urlService;
    private final UserService userService;

    @RequestMapping
    public String adminPage(Model model) {
        List<Url> allUrls = urlService.getAllUrls();
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("urlList", allUrls);
        model.addAttribute("userList", allUsers);
        return "admin";
    }

    @RequestMapping(value = "/deleteUrl", method = RequestMethod.DELETE)
    public String deleteUrl(@RequestHeader(value = HttpHeaders.REFERER, required = false) final String referrer, @RequestParam("urlId") Long id) {
        urlService.deleteUrl(id);
        return "redirect:" + referrer;
    }
}
