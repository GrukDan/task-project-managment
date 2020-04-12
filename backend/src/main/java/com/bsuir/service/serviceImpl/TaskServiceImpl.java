package com.bsuir.service.serviceImpl;

import com.bsuir.model.*;
import com.bsuir.model.viewModel.TaskViewModel;
import com.bsuir.repository.*;
import com.bsuir.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public Task getTaskById(long idtask) {
        return null;
    }

    @Override
    public List<Task> getAllTask() {
        return null;
    }

    @Override
    public List<Task> getAllByExecutor(long taskExecutor) {
        return null;
    }

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
        List<TaskViewModel> taskViewModelList = new ArrayList<>();
        List<Priority> priorityList = (List<Priority>) priorityRepository.findAll();
        List<Status> statusList = (List<Status>) statusRepository.findAll();
        List<User> userList = userRepository.findAll();
        Project project1 = projectRepository.getOne(project);

        for (Task task : taskList) {
            TaskViewModel taskViewModel = new TaskViewModel(task);
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
                        taskViewModel.setTaskCreatorSurname(user.getUserSurname());});
            userList.stream()
                    .filter(user -> taskViewModel.getTaskExecutor() == user.getIduser())
                    .forEach(user -> {
                        taskViewModel.setTaskExecutorName(user.getUserName());
                        taskViewModel.setTaskExecutorSurname(user.getUserSurname());});

            taskViewModel.setProjectName(project1.getProjectName());
            taskViewModelList.add(taskViewModel);
        }
        return taskViewModelList;
    }
}
