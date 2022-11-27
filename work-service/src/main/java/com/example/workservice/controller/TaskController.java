package com.example.workservice.controller;

import com.example.workservice.model.Task;
import com.example.workservice.model.valueObjects.TaskWithUserResponseTemplateVO;
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

    @PostMapping("/{byUserId}/{forUserId}")
    public Task saveTask(@RequestBody Task task, @PathVariable Long byUserId, @PathVariable Long forUserId) {
        //todo: change createdForUser to be in body instead of path variable
        task.setCreatedByUser(byUserId);
        task.setCreatedForUser(forUserId);
        return this.taskService.save(task);
    }

    @PostMapping("/remove/{id}")
    public Task removeTask(@PathVariable Long id) {
        Task task = this.taskService.findById(id);
        return this.taskService.remove(task);
    }

    @GetMapping("/{id}")
    public TaskWithUserResponseTemplateVO getTaskWithUserForUser(@PathVariable("id") Long taskId) {
        //the task created has the user listed as created FOR him
        return this.taskService.getTaskWithUserForUser(taskId);
    }
}
