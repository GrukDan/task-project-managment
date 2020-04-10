package com.bsuir.service.serviceImpl;

import com.bsuir.model.Task;
import com.bsuir.repository.TaskRepository;
import com.bsuir.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

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
}
