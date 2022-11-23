package com.example.feedbackservice.controller;

import com.example.feedbackservice.model.UserFeedback;
import com.example.feedbackservice.service.implementation.UserFeedbackServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feedback/user")
public class UserFeedbackController {
    @Autowired
    private UserFeedbackServiceImplementation userFeedbackService;

    @PostMapping("/{id}")
    public UserFeedback createUserFeedback(@PathVariable Long id,  @RequestBody UserFeedback feedback) {
        return this.userFeedbackService.createFeedbackForUser(id, feedback);
    }

    @GetMapping("/{id}")
    public UserFeedback getUserFeedback(@PathVariable Long id) {
        return this.userFeedbackService.getFeedbackForUser(id);
    }
}
