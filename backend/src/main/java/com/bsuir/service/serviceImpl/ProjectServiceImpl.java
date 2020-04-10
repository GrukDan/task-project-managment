package com.bsuir.service.serviceImpl;

import com.bsuir.model.Project;
import com.bsuir.model.User;
import com.bsuir.model.paginationModel.ProjectPaginationModel;
import com.bsuir.model.viewModel.ProjectForTask;
import com.bsuir.model.viewModel.ProjectViewModel;
import com.bsuir.repository.ProjectRepository;
import com.bsuir.repository.UserRepository;
import com.bsuir.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    private String[] parameterForSorting = {"projectName", "dateOfCompletion", "readinessDegree"};

    @Override
    public Project save(Project project) {
        if (projectRepository.findByProjectNameIgnoreCase(project.getProjectName()) != null) {
            return null;
        }
        String projectCode = project.getProjectName() + " " + (int) (Math.random() * 100);
        project.setProjectCode(projectCode);
        if (projectRepository.findByProjectCode(project.getProjectCode()) == null) {
            return projectRepository.save(project);
        } else return null;
    }

    @Override
    public Project getProjectById(long idproject) {
        return null;
    }

    @Override
    public List<Project> getAllProject() {
        return null;
    }

    @Override
    public List<String> getSortParameter() {
        return Arrays.asList(parameterForSorting);
    }


    @Override
    public ProjectPaginationModel getSortedProject(String parameter, int page, int size, boolean direction) {
        Page<Project> projectPage;
        if (direction)
            projectPage = projectRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, parameter)));
        else
            projectPage = projectRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, parameter)));

        List<Project> projectList = projectPage.getContent();

        //список id юзеров, которые указаны в проектах без повторений
        List<Long> idusers = new ArrayList<>();
        for (Project project : projectList) {
            if (!idusers.contains(project.getProjectCreator())) {
                idusers.add(project.getProjectCreator());
            }
        }

        //поиск только юзеров-создателей проектов
        List<User> users = userRepository.findByIduserIn(idusers);
        List<ProjectViewModel> projectViewModelList = new ArrayList<>();
        for (Project project : projectList) {
            ProjectViewModel projectViewModel = new ProjectViewModel(project);
            for (User user : users) {//цикл установки имени и фамилии создателя проекта
                if (user.getIduser() == projectViewModel.getProjectCreator()) {
                    projectViewModel.setProjectCreatorName(user.getUserName());
                    projectViewModel.setProjectCreatorSurname(user.getUserSurname());
                    break;
                }
            }
            projectViewModelList.add(projectViewModel);
        }

        ProjectViewModel[] projectViewModelArray = new ProjectViewModel[projectViewModelList.size()];
        projectViewModelList.toArray(projectViewModelArray);
        return new ProjectPaginationModel(projectPage.getTotalPages(), page, projectViewModelArray);
    }

    @Override
    public List<ProjectViewModel> projectToProjectViewModel() {
        List<Project> projects = projectRepository.findAll();

        //список id юзеров, которые указаны в проектах без повторений
        List<Long> idusers = new ArrayList<>();
        for (Project project : projects) {
            if (!idusers.contains(project.getProjectCreator())) {
                idusers.add(project.getProjectCreator());
            }
        }
        //поиск только юзеров-создателей проектов
        List<User> users = userRepository.findByIduserIn(idusers);
        List<ProjectViewModel> projectViewModels = new ArrayList<>();
        for (Project project : projects) {
            ProjectViewModel projectViewModel = new ProjectViewModel(project);
            for (User user : users) {//цикл установки имени и фамилии создателя проекта
                if (user.getIduser() == projectViewModel.getProjectCreator()) {
                    projectViewModel.setProjectCreatorName(user.getUserName());
                    projectViewModel.setProjectCreatorSurname(user.getUserSurname());
                    break;
                }
            }
            projectViewModels.add(projectViewModel);
        }
        return projectViewModels;
    }

    @Override
    public List<ProjectForTask> projectToProjectForTask() {
        List<Project> projects = projectRepository.findAll();
        List<ProjectForTask> projectForTasks = new ArrayList<>();
        for (Project project : projects) {
            projectForTasks.add(new ProjectForTask(project));
        }
        return projectForTasks;
    }
}
