package com.bsuir.repository;

import com.bsuir.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findByProjectNameIgnoreCase(String projectName);

    Project findByProjectCode(String projectCode);

    List<Project> findByIdprojectIn(Collection<Long> idproject);
}
