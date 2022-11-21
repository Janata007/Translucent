package com.example.translucentfe.controller;

import com.example.translucentfe.model.NewUserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    @GetMapping("/register")
    public String registerUser(Model model) {
        NewUserDTO newUserDTO = new NewUserDTO();
        model.addAttribute(newUserDTO);
        return "createUser";
    }

    @PostMapping("/register")
    public String registerUser() {
        return "redirect:/users";
    }
}