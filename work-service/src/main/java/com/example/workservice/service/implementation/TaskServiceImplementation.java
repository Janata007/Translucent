package com.example.workservice.service.implementation;

import com.example.workservice.model.Task;
import com.example.workservice.model.valueObjects.AppUser;
import com.example.workservice.model.valueObjects.TaskFeedback;
import com.example.workservice.model.valueObjects.TaskFeedbackList;
import com.example.workservice.model.valueObjects.TaskWithFeedbackResponseTemplateVO;
import com.example.workservice.model.valueObjects.TaskWithUserResponseTemplateVO;
import com.example.workservice.repository.TaskRepository;
import com.example.workservice.service.TaskService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class TaskServiceImplementation implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private RestTemplate restTemplate;


    @Override
    public Task save(Task task) {
        return this.taskRepository.save(task);
    }

    @Override
    public Task remove(Task task) {
        this.taskRepository.delete(task);
        return task;
    }

    @Override
    public Task findById(Long id) {
        return this.taskRepository.findById(id).orElseThrow();
    }

    @Override
    public TaskWithUserResponseTemplateVO getTaskWithUserForUser(Long taskId, String token) {
        TaskWithUserResponseTemplateVO vo = new TaskWithUserResponseTemplateVO();
        Task task = this.taskRepository.findById(taskId).orElseThrow();
        log.info("Task service, GET TASK WITH USER: task fetched " + task.getName());
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<AppUser> responseUserFetch =
            restTemplate.exchange("http://USER-SERVICE/users/simpleUser/" + task.getCreatedForUserId(), HttpMethod.GET,
                requestEntity, AppUser.class);
        vo.setAppUser(responseUserFetch.getBody());
        vo.setTask(task);
        return vo;
    }

    @Override
    public TaskWithFeedbackResponseTemplateVO getTaskWithFeedbacks(Long taskId, String token) {
        TaskWithFeedbackResponseTemplateVO vo = new TaskWithFeedbackResponseTemplateVO();
        Task task = this.taskRepository.findById(taskId).orElseThrow();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        System.out.println("TOKEN: " + token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        log.info("Task service, GET TASK WTIH FEEDBACK LIST: task fetched " + task.getName());
        List<TaskFeedback> feedbacks = new ArrayList<>();
        ResponseEntity<List<TaskFeedback>> feedbackList =
                restTemplate.exchange("http://FEEDBACK-SERVICE/feedback/task/for/" + taskId, HttpMethod.GET, requestEntity,
                        new ParameterizedTypeReference<List<TaskFeedback>>() {});
        feedbacks = feedbackList.getBody();
        vo.setTask(task);
        vo.setFeedback(feedbacks);
        return vo;
    }

    @Override
    public List<Task> getTasksForUser(Long userId) {
        return this.taskRepository.findAllByCreatedForUserId(userId);
    }

    @Override
    public List<Task> getAllTasks() {
        return this.taskRepository.findAll();
    }
}
