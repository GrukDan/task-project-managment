package com.bsuir.service;

import com.bsuir.model.Task;

import java.util.List;

public interface TaskService {

    Task getTaskById(long idtask);
    List<Task> getAllTask();
    List<Task> getAllByExecutor(long taskExecitor);
    List<Task> getByProject(long project);
    Task saveTask(Task task);

}
