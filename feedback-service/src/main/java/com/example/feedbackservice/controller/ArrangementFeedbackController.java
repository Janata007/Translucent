package com.example.feedbackservice.controller;

import com.example.feedbackservice.model.ArrangementFeedback;
import com.example.feedbackservice.service.implementation.ArrangementFeedbackServiceImplementation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feedback/arrangement")
public class ArrangementFeedbackController {
    @Autowired
    private ArrangementFeedbackServiceImplementation arrangementFeedbackService;

    @PostMapping("/{arrangementId}/{userFromId}")
    public ArrangementFeedback createArrangementFeedback(@PathVariable Long arrangementId,
                                                         @PathVariable Long userFromId, @RequestBody
                                                         ArrangementFeedback feedback) {
        feedback.setArrangementId(arrangementId);
        feedback.setUserFromId(userFromId);
        return this.arrangementFeedbackService.createFeedbackForArrangement(feedback);
    }

    @GetMapping("/{id}")
    public List<ArrangementFeedback> getArrangementFeedbacks(@PathVariable Long id) {
        return this.arrangementFeedbackService.getFeedbacksForArrangement(id);
    }
}
