package com.bsuir.repository;

import com.bsuir.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findByTaskCode(String taskCode);

    List<Task> findByProject(Long project);

    List<Task> findByTaskExecutor(Long taskExecutor);

    List<Task> findByTaskNameContainingIgnoreCaseOrTaskCodeContainingIgnoreCaseOrderByTaskName(String taskName, String taskCode);
}
