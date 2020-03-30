package com.bsuir.service;

import com.bsuir.model.Project;

import java.util.List;

public interface ProjectService {
    Project getProjectById(long idproject);
    List<Project> getAllProject();
}
