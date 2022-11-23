package com.example.workservice.service.implementation;

import com.example.workservice.entity.Task;
import com.example.workservice.entity.valueObjects.AppUser;
import com.example.workservice.entity.valueObjects.ResponseTemplateVO;
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
    public ResponseTemplateVO getTaskWithUser(Long taskId) {
        ResponseTemplateVO vo = new ResponseTemplateVO();
        Task task = this.taskRepository.findById(taskId).orElseThrow();
        log.info("Task service, GET TASK WITH USER: task fetched " + task.getUserId());
        AppUser user =
            restTemplate.getForObject("http://USER-SERVICE/users/simpleUser/" + task.getUserId(), AppUser.class);
        vo.setAppUser(user);
        vo.setTask(task);
        return vo;
    }
}
