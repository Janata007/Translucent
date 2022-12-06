package com.example.feedbackservice.service;

import com.example.feedbackservice.model.TaskFeedback;
import java.util.List;

public interface TaskFeedbackService {
    List<TaskFeedback> getFeedbacksForTask(Long taskId);

    TaskFeedback createFeedbackForTask(TaskFeedback feedback);
}
