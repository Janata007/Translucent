package com.example.feedbackservice.controller;

import com.example.feedbackservice.model.TaskFeedback;
import com.example.feedbackservice.service.implementation.TaskFeedbackServiceImplementation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feedback/task")
public class TaskFeedbackController {
    @Autowired
    private TaskFeedbackServiceImplementation taskFeedbackService;

    @PostMapping("/{taskId}/{userFromId}")
    public TaskFeedback createTaskFeedback(@PathVariable Long taskId, @PathVariable Long userFromId,
                                           @RequestBody TaskFeedback feedback) {
        feedback.setTaskId(taskId);
        feedback.setUserFromId(userFromId);
        return this.taskFeedbackService.createFeedbackForTask(feedback);
    }

    @GetMapping("/for/{id}")
    public List<TaskFeedback> getTaskFeedback(@PathVariable Long id) {
        return this.taskFeedbackService.getFeedbacksForTask(id);
    }
}
