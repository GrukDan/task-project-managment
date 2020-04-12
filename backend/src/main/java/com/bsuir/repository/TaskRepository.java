package com.bsuir.repository;

import com.bsuir.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    Task findByTaskName(String taskName);
    Task findByTaskCode(String taskCode);
    List<Task> findByProject(Long project);
}
