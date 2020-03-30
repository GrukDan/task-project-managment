package com.bsuir.service.serviceImpl;

import com.bsuir.model.Project;
import com.bsuir.repository.ProjectRepository;
import com.bsuir.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project getProjectById(long idproject) {
        return null;
    }

    @Override
    public List<Project> getAllProject() {
        return null;
    }
}
