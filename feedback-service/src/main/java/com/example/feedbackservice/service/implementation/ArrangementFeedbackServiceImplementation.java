package com.example.feedbackservice.service.implementation;

import com.example.feedbackservice.model.ArrangementFeedback;
import com.example.feedbackservice.repository.ArrangementFeedbackRepository;
import com.example.feedbackservice.service.ArrangementFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArrangementFeedbackServiceImplementation implements ArrangementFeedbackService {
    @Autowired
    private ArrangementFeedbackRepository arrangementFeedbackRepository;

    @Override
    public ArrangementFeedback getFeedbackForArrangement(Long arrangementId) {
        return this.arrangementFeedbackRepository.findById(arrangementId).orElseThrow();
    }
}
