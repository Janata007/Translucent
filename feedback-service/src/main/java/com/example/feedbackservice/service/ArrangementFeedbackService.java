package com.example.feedbackservice.service;

import com.example.feedbackservice.model.ArrangementFeedback;
import java.util.List;

public interface ArrangementFeedbackService {
    List<ArrangementFeedback> getFeedbacksForArrangement(Long arrangementId);

    ArrangementFeedback createFeedbackForArrangement(ArrangementFeedback feedback);
}
