package com.bsuir.service.serviceImpl;

import com.bsuir.model.Project;
import com.bsuir.model.User;
import com.bsuir.model.httpModel.ProjectForTask;
import com.bsuir.model.httpModel.ProjectViewModel;
import com.bsuir.repository.ProjectRepository;
import com.bsuir.repository.UserRepository;
import com.bsuir.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean save(Project project) {
        if (projectRepository.findByProjectNameIgnoreCase(project.getProjectName()) != null) {
            return false;
        }
        String projectCode = project.getProjectName() + " " + (int)(Math.random() * 100);
        project.setProjectCode(projectCode);
        projectRepository.save(project);
        return true;
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
    public List<ProjectViewModel> projectToProjectViewModel() {
        List<Project> projects = projectRepository.findAll();
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
