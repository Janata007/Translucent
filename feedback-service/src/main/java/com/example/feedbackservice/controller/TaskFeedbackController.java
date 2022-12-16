package com.example.feedbackservice.controller;

import com.example.feedbackservice.model.TaskFeedback;
import com.example.feedbackservice.service.TokenService;
import com.example.feedbackservice.service.implementation.TaskFeedbackServiceImplementation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feedback/task")
public class TaskFeedbackController {
    @Autowired
    private TaskFeedbackServiceImplementation taskFeedbackService;
    @Value("${jwt.secret}")
    private String secretKey;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/{taskId}/{userFromId}")
    public TaskFeedback createTaskFeedback(@RequestHeader("Authorization") String token, @PathVariable Long taskId,
                                           @PathVariable Long userFromId,
                                           @RequestBody TaskFeedback feedback) {
        try {
            this.tokenService.tokenValidated(token, secretKey);
        } catch (Exception e) {
        }
        feedback.setTaskId(taskId);
        feedback.setUserFromId(userFromId);
        return this.taskFeedbackService.createFeedbackForTask(feedback);
    }

    @GetMapping("/for/{id}")
    public List<TaskFeedback> getTaskFeedback(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        try {
            this.tokenService.tokenValidated(token, secretKey);
        } catch (Exception e) {
        }
        return this.taskFeedbackService.getFeedbacksForTask(id);
    }
}
