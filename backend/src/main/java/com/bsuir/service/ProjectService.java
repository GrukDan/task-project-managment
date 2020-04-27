package com.bsuir.service;

import com.bsuir.model.Project;
import com.bsuir.model.paginationModel.ProjectPaginationModel;
import com.bsuir.model.viewModel.ProjectForTask;
import com.bsuir.model.viewModel.ProjectViewModel;

import java.util.List;

public interface ProjectService {

    Project save(Project project);

    void delete(long idproject);

    Project getProjectById(long idproject);

    List<Project> getAllProject();

    List<String> getSortParameter();

    ProjectPaginationModel getSortedProject(String parameter, int page, int size, boolean direction);

    List<ProjectViewModel> projectToProjectViewModel();

    List<ProjectForTask> projectToProjectForTask();

    ProjectViewModel getProjectViewModelById(long id);

    ProjectViewModel saveProjectViewModel(ProjectViewModel projectViewModel);

    ProjectViewModel saveProject(Project project);
}
