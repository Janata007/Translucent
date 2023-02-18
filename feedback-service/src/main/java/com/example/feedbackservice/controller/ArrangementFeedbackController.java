package com.example.feedbackservice.controller;

import com.example.feedbackservice.model.ArrangementFeedback;
import com.example.feedbackservice.service.TokenService;
import com.example.feedbackservice.service.implementation.ArrangementFeedbackServiceImplementation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feedback/arrangement")
public class ArrangementFeedbackController {
    @Autowired
    private ArrangementFeedbackServiceImplementation arrangementFeedbackService;
    @Value("${jwt.secret}")
    private String secretKey;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/{arrangementId}/{userFromId}")
    public ResponseEntity<ArrangementFeedback> createArrangementFeedback(@RequestHeader("Authorization") String token,
                                                                         @PathVariable Long arrangementId,
                                                                         @PathVariable Long userFromId, @RequestBody
                                                                         ArrangementFeedback feedback) {
        try {
            this.tokenService.validateToken(token, secretKey);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        feedback.setArrangementId(arrangementId);
        feedback.setUserFromId(userFromId);
        ArrangementFeedback returnFeedback = this.arrangementFeedbackService.createFeedbackForArrangement(feedback);
        return new ResponseEntity<>(returnFeedback, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ArrangementFeedback>> getArrangementFeedbacks(
        @RequestHeader("Authorization") String token,
        @PathVariable Long id) {
        try {
            this.tokenService.validateToken(token, secretKey);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        List<ArrangementFeedback> feedbackList = this.arrangementFeedbackService.getFeedbacksForArrangement(id);
        return new ResponseEntity<>(feedbackList, HttpStatus.OK);
    }
}
