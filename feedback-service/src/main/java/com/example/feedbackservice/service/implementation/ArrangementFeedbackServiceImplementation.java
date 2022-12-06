package com.example.feedbackservice.service.implementation;

import com.example.feedbackservice.model.ArrangementFeedback;
import com.example.feedbackservice.repository.ArrangementFeedbackRepository;
import com.example.feedbackservice.service.ArrangementFeedbackService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArrangementFeedbackServiceImplementation implements ArrangementFeedbackService {
    @Autowired
    private ArrangementFeedbackRepository arrangementFeedbackRepository;

    @Override
    public List<ArrangementFeedback> getFeedbacksForArrangement(Long arrangementId) {
        return this.arrangementFeedbackRepository.findAllByArrangementId(arrangementId);
    }

    @Override
    public ArrangementFeedback createFeedbackForArrangement(ArrangementFeedback feedback) {
        return this.arrangementFeedbackRepository.save(feedback);
    }
}
