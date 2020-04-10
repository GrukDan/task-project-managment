package com.bsuir.repository;

import com.bsuir.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    Task findByTaskName(String taskName);
    Task findByTaskCode(String taskCode);
}
