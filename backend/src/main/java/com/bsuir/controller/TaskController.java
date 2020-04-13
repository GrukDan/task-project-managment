package com.bsuir.controller;

import com.bsuir.model.Task;
import com.bsuir.model.paginationModel.TaskPaginationModel;
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

    @RequestMapping(value = "/sort-parameter", method = RequestMethod.GET)
    public List<String> getSortParameter() {
        return taskService.getSortParameter();
    }

    @RequestMapping(value = "/sort", method = RequestMethod.GET)
    public TaskPaginationModel getSortedTask(
            @RequestParam("parameter") String parameter,
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("direction") boolean direction,
            @RequestParam("search") String search) {
        return taskService.getSortedTask(parameter, page, size, direction);
    }

    @RequestMapping(method = RequestMethod.GET)
    public TaskViewModel getTaskViewModelById(@RequestParam("id")long id){
        return taskService.getTaskViewModelById(id);
    }

    @RequestMapping(value = "/task-view-model",method = RequestMethod.POST)
    public TaskViewModel saveTaskViewModel(@RequestBody TaskViewModel taskViewModel){
        System.out.println(taskViewModel.toString());
        return taskService.saveTaskViewModel(taskViewModel);
    }

    @RequestMapping(value = "/task-view-model/executor",method = RequestMethod.GET)
    public List<TaskViewModel> getTaskViewModelByTaskExecutor(@RequestParam("executor") long executor){
        System.out.println(executor);
        return taskService.getTaskViewModelByTaskExecutor(executor);
    }
}
