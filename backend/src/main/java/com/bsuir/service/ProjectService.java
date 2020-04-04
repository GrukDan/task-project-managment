package com.bsuir.service;

import com.bsuir.model.Project;
import com.bsuir.model.httpModel.ProjectForTask;
import com.bsuir.model.httpModel.ProjectViewModel;

import java.util.List;

public interface ProjectService {

    boolean save(Project project);
    Project getProjectById(long idproject);
    List<Project> getAllProject();
    List<ProjectViewModel> projectToProjectViewModel();
    List<ProjectForTask> projectToProjectForTask();
}
