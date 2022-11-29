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

    @PostMapping("/{arrangementId}")
    public ArrangementFeedback createArrangementFeedback(@PathVariable Long arrangementId, @RequestBody
    ArrangementFeedback feedback) {
        return this.arrangementFeedbackService.createFeedbackForArrangement(arrangementId, feedback);
    }

    @GetMapping("/{id}")
    public List<ArrangementFeedback> getArrangementFeedbacks(@PathVariable Long id) {
        return this.arrangementFeedbackService.getFeedbacksForArrangement(id);
    }
}
