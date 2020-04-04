package com.bsuir.controller;


import com.bsuir.model.Project;
import com.bsuir.model.httpModel.ProjectForTask;
import com.bsuir.model.httpModel.ProjectViewModel;
import com.bsuir.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/views",method = RequestMethod.GET)
    public List<ProjectViewModel> getAllProjectViewModels(){
        return projectService.projectToProjectViewModel();
    }

    @RequestMapping(value = "/for-task",method = RequestMethod.GET)
    public List<ProjectForTask> getAllProjectForTask(){
        return projectService.projectToProjectForTask();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public boolean save(@RequestBody Project project){
        System.out.println("1111111111111111");
        System.out.println(project.toString() + "!1111111111111111");
        return projectService.save(project);
    }
}
