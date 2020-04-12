package com.bsuir.controller;

import com.bsuir.model.Task;
import com.bsuir.model.viewModel.TaskViewModel;
import com.bsuir.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @RequestMapping(method = RequestMethod.PUT)
    public Task save(@RequestBody Task task){
        return taskService.saveTask(task);
    }

    @RequestMapping(value = "/task-view-model",method = RequestMethod.GET)
    public List<TaskViewModel> getTaskViewModelsByProject(@RequestParam("project")long project){
        return taskService.getTaskViewModelsByProject(project);
    }
}
