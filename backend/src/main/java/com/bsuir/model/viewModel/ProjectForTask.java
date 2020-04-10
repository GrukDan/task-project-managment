package com.bsuir.model.viewModel;

import com.bsuir.model.Project;

public class ProjectForTask {

    private long idproject;
    private String projectName;

    public ProjectForTask(){}

    public ProjectForTask(long idproject, String projectName) {
        this.idproject = idproject;
        this.projectName = projectName;
    }

    public ProjectForTask(Project project){
        this.idproject = project.getIdproject();
        this.projectName = project.getProjectName();
    }

    @Override
    public String toString() {
        return "ProjectForTask{" +
                "idproject=" + idproject +
                ", projectName='" + projectName + '\'' +
                '}';
    }

    public long getIdproject() {
        return idproject;
    }

    public void setIdproject(long idproject) {
        this.idproject = idproject;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
