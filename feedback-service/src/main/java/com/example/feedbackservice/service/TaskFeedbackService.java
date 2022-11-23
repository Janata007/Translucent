package com.example.feedbackservice.service;

import com.example.feedbackservice.model.TaskFeedback;

public interface TaskFeedbackService {
    public TaskFeedback getFeedbackForTask(Long taskId);
}
