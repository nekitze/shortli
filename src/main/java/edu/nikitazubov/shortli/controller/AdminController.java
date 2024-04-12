package edu.nikitazubov.shortli.controller;

import edu.nikitazubov.shortli.entity.Url;
import edu.nikitazubov.shortli.entity.User;
import edu.nikitazubov.shortli.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/admin")
    public String adminPage(Model model) {
        List<Url> allUrls = adminService.getAllUrls();
        List<User> allUsers = adminService.getAllUsers();
        model.addAttribute("urlList", allUrls);
        model.addAttribute("userList", allUsers);
        return "admin";
    }
}
