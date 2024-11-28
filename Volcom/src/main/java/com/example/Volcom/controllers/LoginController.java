package com.example.Volcom.controllers;

import com.example.Volcom.services.RecordService;
import com.example.Volcom.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    private final UserService userService;
    private final RecordService recordService;

    public LoginController(UserService userService, RecordService recordService) {
        this.userService = userService;
        this.recordService = recordService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "records/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        if (userService.authenticate(username, password)) {
            return "redirect:/index";
        } else {
            model.addAttribute("error", "Invalid username or password.");
            return "records/login";
        }
    }

    @GetMapping("/index")
    public String indexPage(Model model) {
        model.addAttribute("records", recordService.getAllRecords());
        return "records/index";
    }
}
