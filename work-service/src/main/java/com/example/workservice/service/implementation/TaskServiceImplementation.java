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
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public TaskWithUserResponseTemplateVO getTaskWithUserForUser(Long taskId) {
        TaskWithUserResponseTemplateVO vo = new TaskWithUserResponseTemplateVO();
        Task task = this.taskRepository.findById(taskId).orElseThrow();
        log.info("Task service, GET TASK WITH USER: task fetched " + task.getName());
        AppUser user =
            restTemplate.getForObject("http://USER-SERVICE/users/simpleUser/" + task.getCreatedForUserId(),
                AppUser.class);
        vo.setAppUser(user);
        vo.setTask(task);
        return vo;
    }

    @Override
    public TaskWithFeedbackResponseTemplateVO getTaskWithFeedbacks(Long taskId) {
        TaskWithFeedbackResponseTemplateVO vo = new TaskWithFeedbackResponseTemplateVO();
        Task task = this.taskRepository.findById(taskId).orElseThrow();
        log.info("Task service, GET TASK WTIH FEEDBACK LIST: task fetched " + task.getName());
        List<TaskFeedback> feedbacks = new ArrayList<>();
        TaskFeedbackList
            //todo: check if json is properly deserialized like this
            feedbackList =
            restTemplate.getForObject("http://FEEDBACK-SERVICE/feedback/task/" + taskId, TaskFeedbackList.class);
        feedbacks = feedbackList.getFeedbackList();
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
