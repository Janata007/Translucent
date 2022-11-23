package com.example.workservice.controller;

import com.example.workservice.entity.Task;
import com.example.workservice.entity.valueObjects.ResponseTemplateVO;
import com.example.workservice.service.implementation.TaskServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/work")
public class TaskController {
    @Autowired
    private TaskServiceImplementation taskService;

    @PostMapping("/{userId}")
    public Task saveTask(@RequestBody Task task, @PathVariable Long userId) {
        task.setUserId(userId);
        return this.taskService.save(task);

    }

    @PostMapping("/remove/{id}")
    public Task removeTask(@PathVariable Long id) {
        Task task = this.taskService.findById(id);
        return this.taskService.remove(task);
    }

    @GetMapping("/{id}")
    public ResponseTemplateVO getTaskWithUser(@PathVariable("id") Long taskId) {
        return this.taskService.getTaskWithUser(taskId);
    }
}
