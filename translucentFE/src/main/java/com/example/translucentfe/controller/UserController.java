package com.example.translucentfe.controller;

import com.example.translucentfe.model.NewUser;
import com.example.translucentfe.service.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImplementation userService;

    @GetMapping("/register")
    public String registerUser(Model model) {
        NewUser newUserDTO = new NewUser();
        model.addAttribute(newUserDTO);
        return "createUser";
    }

    @GetMapping("/")
    public String homePage(Model model) {
        return "homePage";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        return "loginPage";
    }

    @PostMapping("/login")
    public String userLogin() {
        return "redirect:/users/";
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody NewUser user) {
        this.userService.registerUser(user);
        return "redirect:/users/";
    }
}
