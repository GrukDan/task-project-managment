package com.bsuir.repository;

import com.bsuir.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
    Project findByProjectNameIgnoreCase(String projectName);
}
