package edu.nikitazubov.shortli.controller;

import edu.nikitazubov.shortli.entity.User;
import edu.nikitazubov.shortli.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SignUpController {
    private final UserService userService;

    @GetMapping("/signup")
    public String signUp(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "/signup";
    }

    @PostMapping("/signup")
    public String createUser(@ModelAttribute("user") User user, Model model) {
        try {
            User existedUser = userService.addNewUser(user);
        } catch (Exception e) {
            model.addAttribute("existedUser", true);
            return "signup";
        }
        return "redirect:/login";
    }
}
