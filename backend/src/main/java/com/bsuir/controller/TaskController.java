package com.bsuir.controller;

import com.bsuir.model.Task;
import com.bsuir.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;


    @RequestMapping(method = RequestMethod.PUT)
    public Task save(@RequestBody Task task){
        return taskService.saveTask(task);
    }
}
