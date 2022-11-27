package com.example.workservice.service.implementation;

import com.example.workservice.model.Task;
import com.example.workservice.model.valueObjects.AppUser;
import com.example.workservice.model.valueObjects.TaskWithUserResponseTemplateVO;
import com.example.workservice.repository.TaskRepository;
import com.example.workservice.service.TaskService;
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
        log.info("Task service, GET TASK WITH USER: task fetched " + task.getCreatedForUser());
        AppUser user =
            restTemplate.getForObject("http://USER-SERVICE/users/simpleUser/" + task.getCreatedForUser(), AppUser.class);
        vo.setAppUser(user);
        vo.setTask(task);
        return vo;
    }
}
