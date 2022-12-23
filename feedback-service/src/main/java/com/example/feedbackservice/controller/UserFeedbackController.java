package com.example.feedbackservice.controller;

import com.example.feedbackservice.model.UserFeedback;
import com.example.feedbackservice.service.TokenService;
import com.example.feedbackservice.service.implementation.UserFeedbackServiceImplementation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feedback/user")
public class UserFeedbackController {
    @Autowired
    private UserFeedbackServiceImplementation userFeedbackService;
    @Value("${jwt.secret}")
    private String secretKey;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/{userForId}/{userFromId}")
    public UserFeedback createUserFeedback(@RequestHeader("Authorization") String token, @PathVariable Long userForId,
                                           @PathVariable Long userFromId,
                                           @RequestBody UserFeedback feedback) {
        try {
            this.tokenService.validateToken(token, secretKey);
        } catch (Exception e) {
        }
        feedback.setUserForId(userForId);
        feedback.setUserFromId(userFromId);
        return this.userFeedbackService.createFeedbackForUser(feedback);
    }

    @GetMapping("/for/{id}")
    public List<UserFeedback> getUserFeedback(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        try {
            this.tokenService.validateToken(token, secretKey);
        } catch (Exception e) {
        }
        return this.userFeedbackService.getFeedbacksForUser(id);
    }

    @GetMapping("/from/{id}")
    public List<UserFeedback> getUserFeedbackFrom(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        try {
            this.tokenService.validateToken(token, secretKey);
        } catch (Exception e) {
        }
        return this.userFeedbackService.getFeedbacksFromUser(id);
    }

}
