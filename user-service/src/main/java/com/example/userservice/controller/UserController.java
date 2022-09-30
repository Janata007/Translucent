package com.example.userservice.controller;

import com.example.userservice.entity.AppUser;
import com.example.userservice.entity.ValueObjects.ResponseTemplateVO;
import com.example.userservice.service.implementation.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/")
    public AppUser saveUser(@RequestBody AppUser appUser) {
        log.info("saveUser in UserController");
        return this.userService.saveUser(appUser);
    }
    @GetMapping("/{id}")
    public ResponseTemplateVO getUserWithSector(@PathVariable("id") Long userId){
        return this.userService.getUserWithSector(userId);
    }
}
