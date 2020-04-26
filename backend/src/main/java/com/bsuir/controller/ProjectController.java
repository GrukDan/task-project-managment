package com.bsuir.controller;


import com.bsuir.model.Project;
import com.bsuir.model.paginationModel.ProjectPaginationModel;
import com.bsuir.model.viewModel.ProjectForTask;
import com.bsuir.model.viewModel.ProjectViewModel;
import com.bsuir.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping(method = RequestMethod.PUT)
    public Project save(@RequestBody Project project) {
        return projectService.save(project);
    }

    @RequestMapping(value = "/project",method = RequestMethod.POST)
    public ProjectViewModel saveProject(@RequestBody Project project){
        return projectService.saveProject(project);
    }

    @RequestMapping(value = "/project-view-model",method = RequestMethod.POST)
    public ProjectViewModel saveProject(@RequestBody ProjectViewModel projectViewModel){
        return projectService.saveProjectViewModel(projectViewModel);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ProjectViewModel getProjectViewModelById(@RequestParam("id")long id){
        return projectService.getProjectViewModelById(id);
    }

    @RequestMapping(value = "/views", method = RequestMethod.GET)
    public List<ProjectViewModel> getAllProjectViewModels() {
        return projectService.projectToProjectViewModel();
    }

    @RequestMapping(value = "/all-projects", method = RequestMethod.GET)
    public List<Project> getAllProject() {
        return projectService.getAllProject();
    }

    @RequestMapping(value = "/for-task", method = RequestMethod.GET)
    public List<ProjectForTask> getAllProjectForTask() {
        return projectService.projectToProjectForTask();
    }

    @RequestMapping(value = "/sort-parameter", method = RequestMethod.GET)
    public List<String> getSortParameter() {
        return projectService.getSortParameter();
    }

    @RequestMapping(value = "/sort", method = RequestMethod.GET)
    public ProjectPaginationModel getSortedProject(
            @RequestParam("parameter") String parameter,
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("direction") boolean direction,
            @RequestParam("search") String search) {
        return projectService.getSortedProject(parameter, page, size, direction);
    }
}
