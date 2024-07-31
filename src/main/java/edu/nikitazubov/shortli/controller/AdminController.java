package edu.nikitazubov.shortli.controller;

import edu.nikitazubov.shortli.entity.Url;
import edu.nikitazubov.shortli.entity.User;
import edu.nikitazubov.shortli.entity.admin.AdminParameter;
import edu.nikitazubov.shortli.entity.admin.AdminStatistics;
import edu.nikitazubov.shortli.repository.AdminParameterRepository;
import edu.nikitazubov.shortli.repository.AdminStatisticsRepository;
import edu.nikitazubov.shortli.service.UrlService;
import edu.nikitazubov.shortli.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final UrlService urlService;
    private final UserService userService;

    private final AdminParameterRepository adminParameterRepository;
    private final AdminStatisticsRepository adminStatisticsRepository;

    @GetMapping
    public String adminPage(Model model) {
        List<Url> urls = urlService.getTodayUrls();
        model.addAttribute("urlList", urls);
        loadAdminModel(model);
        return "admin";
    }

    @GetMapping("/getAllUrls")
    public String allUrls(Model model) {
        List<Url> allUrls = urlService.getAllUrls();
        model.addAttribute("urlList", allUrls);
        loadAdminModel(model);
        return "admin";
    }

    @DeleteMapping(value = "/deleteUrl")
    public String deleteUrl(@RequestHeader(value = HttpHeaders.REFERER, required = false) final String referrer, @RequestParam("urlId") Long id) {
        urlService.deleteUrl(id);
        return "redirect:" + referrer;
    }

    @PostMapping(value = "/monetizeUrl")
    public String monetizeUrl(@RequestHeader(value = HttpHeaders.REFERER, required = false) final String referrer, @RequestParam("urlId") Long id) {
        Url url = urlService.getUrlById(id);
        url.setMonetized(!url.isMonetized());
        urlService.updateUrl(url);
        return "redirect:" + referrer;
    }

    @PostMapping(value = "/updateRandomMonetization")
    public String monetizeUrl(@RequestHeader(value = HttpHeaders.REFERER, required = false) final String referrer, @RequestParam("monetizationValue") Float value) {
        AdminParameter monetizationValue = adminParameterRepository.getReferenceById("random_monetization");
        monetizationValue.setValue(value.toString());
        adminParameterRepository.save(monetizationValue);
        return "redirect:" + referrer;
    }

    private void loadAdminModel(Model model) {
        List<User> allUsers = userService.getAllUsers();
        AdminStatistics statistics = adminStatisticsRepository.findById(LocalDate.now()).orElseGet(() -> {
            AdminStatistics s = new AdminStatistics();
            s.setDate(LocalDate.now());
            return s;
        });
        AdminParameter monetizationValue = adminParameterRepository.getReferenceById("random_monetization");
        model.addAttribute("monetizationValue", Float.parseFloat(monetizationValue.getValue()));
        model.addAttribute("userList", allUsers);
        model.addAttribute("statistics", statistics);
    }
}
