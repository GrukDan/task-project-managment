package com.bsuir.controller;

import com.bsuir.model.Task;
import com.bsuir.model.paginationModel.TaskPaginationModel;
import com.bsuir.model.viewModel.TaskViewModel;
import com.bsuir.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

    @RequestMapping(method = RequestMethod.PUT)
    public Task save(@RequestBody Task task){
        LOGGER.info("Save task: " + task.getTaskName());
        return taskService.saveTask(task);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void delete(@RequestParam("idTask") long idTask){
        LOGGER.info("Delete task");
        taskService.delete(idTask);
    }

    @RequestMapping(value = "/task-view-model",method = RequestMethod.GET)
    public List<TaskViewModel> getTaskViewModelsByProject(@RequestParam("project")long project){
        LOGGER.info("Get task by project");
        return taskService.getTaskViewModelsByProject(project);
    }

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public List<TaskViewModel> getTaskViewModelBySearch(@RequestParam("search") String search){
        LOGGER.info("Get tasks by: " + search);
        return taskService.getTaskViewModelBySearch(search);
    }

    @RequestMapping(value = "/sort-parameter", method = RequestMethod.GET)
    public List<String> getSortParameter() {
        LOGGER.info("Get parameters for task sorting");
        return taskService.getSortParameter();
    }

    @RequestMapping(value = "/sort", method = RequestMethod.GET)
    public TaskPaginationModel getSortedTask(
            @RequestParam("parameter") String parameter,
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("direction") boolean direction,
            @RequestParam("search") String search) {
        LOGGER.info("Get sorted users with parameters: " +
                " page: " + String.valueOf(page) +
                " size: " + String.valueOf(size) +
                " direction: " + String.valueOf(direction));
        return taskService.getSortedTask(parameter, page, size, direction);
    }

    @RequestMapping(method = RequestMethod.GET)
    public TaskViewModel getTaskViewModelById(@RequestParam("id")long id){
        return taskService.getTaskViewModelById(id);
    }

    @RequestMapping(value = "/task-view-model",method = RequestMethod.POST)
    public TaskViewModel saveTaskViewModel(@RequestBody TaskViewModel taskViewModel){
        return taskService.saveTaskViewModel(taskViewModel);
    }

    @RequestMapping(value = "/task-view-model/executor",method = RequestMethod.GET)
    public List<TaskViewModel> getTaskViewModelByTaskExecutor(@RequestParam("executor") long executor){
        return taskService.getTaskViewModelByTaskExecutor(executor);
    }
}
