package com.bsuir.service;

import com.bsuir.model.Task;
import com.bsuir.model.paginationModel.TaskPaginationModel;
import com.bsuir.model.viewModel.TaskViewModel;

import java.util.List;

public interface TaskService {

    void delete(long idtask);

    Task saveTask(Task task);

    List<TaskViewModel> getTaskViewModelsByProject(long project);

    List<String> getSortParameter();

    TaskPaginationModel getSortedTask(String parameter, int page, int size, boolean direction);

    TaskViewModel getTaskViewModelById(long id);

    TaskViewModel saveTaskViewModel(TaskViewModel taskViewModel);

    List<TaskViewModel> getTaskViewModelByTaskExecutor(long executor);

    List<TaskViewModel> getTaskViewModelBySearch(String search);
}
