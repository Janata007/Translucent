package com.example.feedbackservice.controller;

import com.example.feedbackservice.model.UserFeedback;
import com.example.feedbackservice.service.TokenService;
import com.example.feedbackservice.service.implementation.UserFeedbackServiceImplementation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/feedback/user")
public class UserFeedbackController {
    @Autowired
    private UserFeedbackServiceImplementation userFeedbackService;
    @Value("${jwt.secret}")
    private String secretKey;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/{userForId}/{userFromId}")
    public ResponseEntity<UserFeedback> createUserFeedback(@RequestHeader("Authorization") String token,
                                                           @PathVariable Long userForId,
                                                           @PathVariable Long userFromId,
                                                           @RequestBody UserFeedback feedback) {
        try {
            this.tokenService.validateToken(token, secretKey);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        feedback.setUserForId(userForId);
        feedback.setUserFromId(userFromId);
        UserFeedback feedbackCreated = this.userFeedbackService.createFeedbackForUser(feedback);
        return new ResponseEntity<>(feedbackCreated, HttpStatus.CREATED);
    }

    @GetMapping("/for/{userId}")
    public ResponseEntity<List<UserFeedback>> getUserFeedback(@RequestHeader("Authorization") String token,
                                                              @PathVariable Long userId) {
        try {
            this.tokenService.validateToken(token, secretKey);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        List<UserFeedback> feedbackList = this.userFeedbackService.getFeedbacksForUser(userId);
        return new ResponseEntity<>(feedbackList, HttpStatus.OK);
    }

    @GetMapping("/from/{userId}")
    public ResponseEntity<List<UserFeedback>> getUserFeedbackFrom(@RequestHeader("Authorization") String token,
                                                                  @PathVariable Long userId) {
        try {
            this.tokenService.validateToken(token, secretKey);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        List<UserFeedback> feedbackList = this.userFeedbackService.getFeedbacksFromUser(userId);
        return new ResponseEntity<>(feedbackList, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserFeedback>> getUserFeedbackFrom(@RequestHeader("Authorization") String token) {
        try {
            this.tokenService.validateToken(token, secretKey);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        List<UserFeedback> feedbackList = this.userFeedbackService.getAllUserFeedbacks();
        return new ResponseEntity<>(feedbackList, HttpStatus.OK);
    }
}
