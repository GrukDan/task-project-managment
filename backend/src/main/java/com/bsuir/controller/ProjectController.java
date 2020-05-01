package com.bsuir.controller;


import com.bsuir.model.Project;
import com.bsuir.model.paginationModel.ProjectPaginationModel;
import com.bsuir.model.viewModel.ProjectForTask;
import com.bsuir.model.viewModel.ProjectViewModel;
import com.bsuir.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);

    @RequestMapping(method = RequestMethod.PUT)
    public Project save(@RequestBody Project project) {
        LOGGER.info("Save project: " + project.getProjectName());
        return projectService.save(project);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void delete(@RequestParam("idProject") long idProject){
        LOGGER.info("Delete project");
        projectService.delete(idProject);
    }

    @RequestMapping(value = "/project",method = RequestMethod.POST)
    public ProjectViewModel saveProject(@RequestBody Project project){
        LOGGER.info("Save project: " + project.getProjectName());
        return projectService.saveProject(project);
    }

    @RequestMapping(value = "/project-view-model",method = RequestMethod.POST)
    public ProjectViewModel saveProject(@RequestBody ProjectViewModel projectViewModel){
        LOGGER.info("Save project: " + projectViewModel.getProjectName());
        return projectService.saveProjectViewModel(projectViewModel);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ProjectViewModel getProjectViewModelById(@RequestParam("id")long id){
        LOGGER.info("Get project");
        return projectService.getProjectViewModelById(id);
    }

    @RequestMapping(value = "/views", method = RequestMethod.GET)
    public List<ProjectViewModel> getAllProjectViewModels() {
        return projectService.projectToProjectViewModel();
    }

    @RequestMapping(value = "/all-projects", method = RequestMethod.GET)
    public List<Project> getAllProject() {
        LOGGER.info("Get all projects");
        return projectService.getAllProject();
    }

    @RequestMapping(value = "/for-task", method = RequestMethod.GET)
    public List<ProjectForTask> getAllProjectForTask() {
        return projectService.projectToProjectForTask();
    }

    @RequestMapping(value = "/sort-parameter", method = RequestMethod.GET)
    public List<String> getSortParameter() {
        LOGGER.info("Get parameters for project sorting");
        return projectService.getSortParameter();
    }

    @RequestMapping(value = "/sort", method = RequestMethod.GET)
    public ProjectPaginationModel getSortedProject(
            @RequestParam("parameter") String parameter,
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("direction") boolean direction,
            @RequestParam("search") String search) {
        LOGGER.info("Get sorted users with parameters " +
                " page: " + String.valueOf(page) +
                " size: " + String.valueOf(size) +
                " direction: " + String.valueOf(direction));
        return projectService.getSortedProject(parameter, page, size, direction);
    }
}
