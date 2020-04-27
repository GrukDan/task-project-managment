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
    public void delete(long idtask) {
        taskRepository.deleteById(idtask);
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
            setValues(taskViewModel,
                    projectRepository.findAll(),
                    statusRepository.findAll(),
                    priorityRepository.findAll(),
                    userRepository.findAll());
            taskViewModel.setProjectName(project1.getProjectName());
            taskViewModelList.add(taskViewModel);
        }
        return taskViewModelList;
    }

    @Override
    public List<String> getSortParameter() {
        return Arrays.asList(parameterForSorting);
    }

    private TaskViewModel setValues(
            TaskViewModel taskViewModel,
            List<Project> projects,
            List<Status> statuses,
            List<Priority> priorities,
            List<User> users) {
        setProjectName(taskViewModel,projects);
        setPriorityName(taskViewModel,priorities);
        setStatusName(taskViewModel,statuses);
        setCreatorNameSurname(taskViewModel,users);
        setExecutorNameSurname(taskViewModel,users);
        return taskViewModel;
    }

    private TaskViewModel setValues(TaskViewModel taskViewModel){
        setProjectName(taskViewModel);
        setPriorityName(taskViewModel);
        setStatusName(taskViewModel);
        setCreatorNameSurname(taskViewModel);
        setExecutorNameSurname(taskViewModel);
        return taskViewModel;
    }

    private TaskViewModel setPriorityName(TaskViewModel taskViewModel,List<Priority> priorities){
        priorities.stream()
                .filter(priority -> taskViewModel.getPriority() == priority.getIdpriority())
                .forEach(priority -> taskViewModel.setPriorityName(priority.getPriority()));
        return taskViewModel;
    }

    private TaskViewModel setProjectName(TaskViewModel taskViewModel,List<Project> projects){
        projects.stream()
                .filter(project -> taskViewModel.getProject() == project.getIdproject())
                .forEach(project -> taskViewModel.setProjectName(project.getProjectName()));
        return taskViewModel;
    }

    private TaskViewModel setStatusName(TaskViewModel taskViewModel,List<Status> statuses){
        statuses.stream()
                .filter(status -> taskViewModel.getStatus() == status.getIdstatus())
                .forEach(status -> taskViewModel.setStatusName(status.getStatus()));
        return taskViewModel;
    }

    private TaskViewModel setCreatorNameSurname(TaskViewModel taskViewModel,List<User> users){
        users.stream()
                .filter(user -> taskViewModel.getTaskCreator() == user.getIduser())
                .forEach(user -> {
                    taskViewModel.setTaskCreatorName(user.getUserName());
                    taskViewModel.setTaskCreatorSurname(user.getUserSurname());
                });
        return taskViewModel;
    }

    private TaskViewModel setExecutorNameSurname(TaskViewModel taskViewModel,List<User> users){
        if(taskViewModel.getTaskExecutor()!=null)
            users.stream()
                    .filter(user -> taskViewModel.getTaskExecutor() == user.getIduser())
                    .forEach(user -> {
                        taskViewModel.setTaskExecutorName(user.getUserName());
                        taskViewModel.setTaskExecutorSurname(user.getUserSurname());
                    });
        return taskViewModel;
    }

    private TaskViewModel setPriorityName(TaskViewModel taskViewModel){
        Priority priority = priorityRepository.getOne(taskViewModel.getPriority());
        taskViewModel.setPriorityName(priority.getPriority());
        return taskViewModel;
    }

    private TaskViewModel setProjectName(TaskViewModel taskViewModel){
        Project project = projectRepository.getOne(taskViewModel.getProject());
        taskViewModel.setProjectName(project.getProjectName());
        return taskViewModel;
    }

    private TaskViewModel setStatusName(TaskViewModel taskViewModel){
        Status status = statusRepository.getOne(taskViewModel.getStatus());
        taskViewModel.setStatusName(status.getStatus());
        return taskViewModel;
    }

    private TaskViewModel setCreatorNameSurname(TaskViewModel taskViewModel){
        User creator = userRepository.getOne(taskViewModel.getTaskCreator());
        taskViewModel.setTaskCreatorName(creator.getUserName());
        taskViewModel.setTaskCreatorSurname(creator.getUserSurname());
        return taskViewModel;
    }

    private TaskViewModel setExecutorNameSurname(TaskViewModel taskViewModel){
        if(taskViewModel.getTaskExecutor()!=null){
            User executor = userRepository.getOne(taskViewModel.getTaskExecutor());
            taskViewModel.setTaskExecutorName(executor.getUserName());
            taskViewModel.setTaskExecutorSurname(executor.getUserSurname());
        }
        return taskViewModel;
    }

    @Override
    public List<TaskViewModel> getTaskViewModelByTaskExecutor(long executor) {
        List<Task> taskList = taskRepository.findByTaskExecutor(executor);
        List<TaskViewModel> taskViewModelList = new ArrayList<>();
        taskList.stream().forEach(task -> taskViewModelList
                .add(setValues(
                        new TaskViewModel(task),
                        projectRepository.findAll(),
                        statusRepository.findAll(),
                        priorityRepository.findAll(),
                        userRepository.findAll())));
        return taskViewModelList;
    }

    @Override
    public List<TaskViewModel> getTaskViewModelBySearch(String search) {
        return taskViewModelsFromTasks(taskRepository
                .findByTaskNameContainingIgnoreCaseOrTaskCodeContainingIgnoreCaseOrderByTaskName(search,search));
    }

    private List<TaskViewModel> taskViewModelsFromTasks(List<Task> tasks){
        List<TaskViewModel> taskViewModels = new ArrayList<>();
        tasks.stream().forEach(task -> {
            taskViewModels.add(setValues(
                    new TaskViewModel(task),
                    projectRepository.findAll(),
                    statusRepository.findAll(),
                    priorityRepository.findAll(),
                    userRepository.findAll()));
        });
        return taskViewModels;
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
        List<TaskViewModel> taskViewModelList = taskViewModelsFromTasks(taskList);
        TaskViewModel[] taskViewModels = new TaskViewModel[taskViewModelList.size()];
        taskViewModelList.toArray(taskViewModels);

        return new TaskPaginationModel(taskPage.getTotalPages(), page, taskViewModels);
    }

    @Override
    public TaskViewModel getTaskViewModelById(long id) {
        TaskViewModel taskViewModel = new TaskViewModel(taskRepository.getOne(id));
         return setValues(taskViewModel);
    }

    @Override
    public TaskViewModel saveTaskViewModel(TaskViewModel taskViewModel) {
        taskRepository.save(taskViewModel.buildTask());
        return getTaskViewModelById(taskViewModel.getIdtask());
    }
}
