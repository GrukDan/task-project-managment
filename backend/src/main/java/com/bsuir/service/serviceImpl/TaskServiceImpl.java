package com.bsuir.service.serviceImpl;

import com.bsuir.model.*;
import com.bsuir.model.paginationModel.TaskPaginationModel;
import com.bsuir.model.viewModel.TaskViewModel;
import com.bsuir.repository.*;
import com.bsuir.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PriorityRepository priorityRepository;

    @Autowired
    private StatusRepository statusRepository;

    private String[] parameterForSorting = {"taskName", "priority", "status", "dateOfCreation", "dueDate", "updated"};


    @Override
    public List<Task> getByProject(long project) {
        return null;
    }

    @Override
    public Task saveTask(Task task) {
        String taskCode = task.getTaskName() + " " + (int) (Math.random() * 100);
        task.setTaskCode(taskCode);
        if (taskRepository.findByTaskCode(task.getTaskCode()) == null) {
            return taskRepository.save(task);
        } else return null;
    }

    @Override
    public List<TaskViewModel> getTaskViewModelsByProject(long project) {
        List<Task> taskList = taskRepository.findByProject(project);
        Project project1 = projectRepository.getOne(project);
        List<TaskViewModel> taskViewModelList = new ArrayList<>();

        for (Task task : taskList) {
            TaskViewModel taskViewModel = new TaskViewModel(task);
            setValues(taskViewModel);
            taskViewModel.setProjectName(project1.getProjectName());
            taskViewModelList.add(taskViewModel);
        }
        return taskViewModelList;
    }

    @Override
    public List<String> getSortParameter() {
        return Arrays.asList(parameterForSorting);
    }


    @Override
    public TaskViewModel setValues(TaskViewModel taskViewModel) {
        List<Priority> priorityList = (List<Priority>) priorityRepository.findAll();
        List<Status> statusList = (List<Status>) statusRepository.findAll();
        List<User> userList = userRepository.findAll();

        priorityList.stream()
                .filter(priority -> taskViewModel.getPriority() == priority.getIdpriority())
                .forEach(priority -> taskViewModel.setPriorityName(priority.getPriority()));

        statusList.stream()
                .filter(status -> taskViewModel.getStatus() == status.getIdstatus())
                .forEach(status -> taskViewModel.setStatusName(status.getStatus()));

        userList.stream()
                .filter(user -> taskViewModel.getTaskCreator() == user.getIduser())
                .forEach(user -> {
                    taskViewModel.setTaskCreatorName(user.getUserName());
                    taskViewModel.setTaskCreatorSurname(user.getUserSurname());
                });

        userList.stream()
                .filter(user -> taskViewModel.getTaskExecutor() == user.getIduser())
                .forEach(user -> {
                    taskViewModel.setTaskExecutorName(user.getUserName());
                    taskViewModel.setTaskExecutorSurname(user.getUserSurname());
                });
        return taskViewModel;
    }

    @Override
    public List<TaskViewModel> getTaskViewModelByTaskExecutor(long executor) {
        List<Task> taskList = taskRepository.findByTaskExecutor(executor);
        List<TaskViewModel> taskViewModelList = new ArrayList<>();
        taskList.stream().forEach(task -> taskViewModelList
                .add(setValues(new TaskViewModel(task))));
        return taskViewModelList;
    }

    @Override
    public TaskPaginationModel getSortedTask(String parameter, int page, int size, boolean direction) {
        Page<Task> taskPage;
        if (direction) {
            taskPage = taskRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, parameter)));
        } else {
            taskPage = taskRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, parameter)));
        }
        List<Task> taskList = taskPage.getContent();
        List<TaskViewModel> taskViewModelList = new ArrayList<>();
        List<Project> projectList = projectRepository.findAll();

        for (Task task : taskList) {
            TaskViewModel taskViewModel = new TaskViewModel(task);

            projectList.stream()
                    .filter(project -> taskViewModel.getProject() == project.getIdproject())
                    .forEach(project -> taskViewModel.setProjectName(project.getProjectName()));
            setValues(taskViewModel);

            taskViewModelList.add(taskViewModel);
        }
        TaskViewModel[] taskViewModels = new TaskViewModel[taskViewModelList.size()];
        taskViewModelList.toArray(taskViewModels);

        return new TaskPaginationModel(taskPage.getTotalPages(), page, taskViewModels);
    }

    @Override
    public TaskViewModel getTaskViewModelById(long id) {
        Task task = taskRepository.getOne(id);
        TaskViewModel taskViewModel = new TaskViewModel(task);

        Project project = projectRepository.getOne(taskViewModel.getProject());
        taskViewModel.setProjectName(project.getProjectName());
        setValues(taskViewModel);

        return taskViewModel;
    }

    @Override
    public TaskViewModel saveTaskViewModel(TaskViewModel taskViewModel) {
        taskRepository.save(taskViewModel.buildTask());
        return getTaskViewModelById(taskViewModel.getIdtask());
    }
}
