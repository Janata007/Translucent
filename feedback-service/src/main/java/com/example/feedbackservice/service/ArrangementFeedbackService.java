package com.example.feedbackservice.service;

import com.example.feedbackservice.model.ArrangementFeedback;

public interface ArrangementFeedbackService {
    public ArrangementFeedback getFeedbackForArrangement(Long arrangementId);
}
