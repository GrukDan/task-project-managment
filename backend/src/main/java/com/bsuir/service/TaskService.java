package com.bsuir.service;

import com.bsuir.model.Task;
import com.bsuir.model.paginationModel.TaskPaginationModel;
import com.bsuir.model.viewModel.TaskViewModel;

import java.util.List;

public interface TaskService {

    List<Task> getByProject(long project);

    Task saveTask(Task task);

    List<TaskViewModel> getTaskViewModelsByProject(long project);

    List<String> getSortParameter();

    TaskPaginationModel getSortedTask(String parameter, int page, int size, boolean direction);

    TaskViewModel getTaskViewModelById(long id);

    TaskViewModel saveTaskViewModel(TaskViewModel taskViewModel);

    TaskViewModel setValues(TaskViewModel taskViewModel);

    List<TaskViewModel> getTaskViewModelByTaskExecutor(long executor);
}
