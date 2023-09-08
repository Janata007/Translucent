package com.example.feedbackservice.service;

import com.example.feedbackservice.controller.ArrangementFeedbackController;
import com.example.feedbackservice.model.ArrangementFeedback;
import java.util.List;

public interface ArrangementFeedbackService {
    List<ArrangementFeedback> getFeedbacksForArrangement(Long arrangementId);
    List<ArrangementFeedback> getAllArrangementFeedbacks();

    ArrangementFeedback createFeedbackForArrangement(ArrangementFeedback feedback);
}
