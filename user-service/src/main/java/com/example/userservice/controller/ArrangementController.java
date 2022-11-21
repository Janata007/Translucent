package com.example.userservice.controller;

import com.example.userservice.entity.Arrangement;
import com.example.userservice.service.implementation.ArrangementServiceImpl;
import com.example.userservice.service.implementation.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/arrangements")
@Slf4j
public class ArrangementController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ArrangementServiceImpl arrangementService;

    @PostMapping("/")
    public Arrangement saveArrangement(@RequestBody Arrangement arrangement) {
        log.info("saveArrangement in ArrangementController");
        return this.arrangementService.saveArrangement(arrangement);
    }
}
