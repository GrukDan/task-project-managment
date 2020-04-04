package com.bsuir.model.httpModel;

import com.bsuir.model.Project;

import java.util.Date;

public class ProjectViewModel {

    private long idproject;
    private String projectCode;
    private String projectName;
    private long projectCreator;
    private Date dateOfCompletion;
    private Double readinessDegree;
    private String projectCreatorName;
    private String projectCreatorSurname;

    public ProjectViewModel(){}

    public ProjectViewModel(Project project){
        this.idproject = project.getIdproject();
        this.projectCode = project.getProjectCode();
        this.projectName = project.getProjectName();
        this.projectCreator = project.getProjectCreator();
        this.dateOfCompletion = project.getDateOfCompletion();
        this.readinessDegree = project.getReadinessDegree();
    }

    public ProjectViewModel(long idproject, String projectCode, String projectName, long projectCreator, Date dateOfCompletion, Double readinessDegree, String projectCreatorName, String projectCreatorSurname) {
        this.idproject = idproject;
        this.projectCode = projectCode;
        this.projectName = projectName;
        this.projectCreator = projectCreator;
        this.dateOfCompletion = dateOfCompletion;
        this.readinessDegree = readinessDegree;
        this.projectCreatorName = projectCreatorName;
        this.projectCreatorSurname = projectCreatorSurname;
    }

    @Override
    public String toString() {
        return "ProjectViewModel{" +
                "idproject=" + idproject +
                ", projectCode='" + projectCode + '\'' +
                ", projectName='" + projectName + '\'' +
                ", projectCreator=" + projectCreator +
                ", dateOfCompletion=" + dateOfCompletion +
                ", readinessDegree=" + readinessDegree +
                ", projectCreatorName='" + projectCreatorName + '\'' +
                ", projectCreatorSurname='" + projectCreatorSurname + '\'' +
                '}';
    }

    public long getIdproject() {
        return idproject;
    }

    public void setIdproject(long idproject) {
        this.idproject = idproject;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public long getProjectCreator() {
        return projectCreator;
    }

    public void setProjectCreator(long projectCreator) {
        this.projectCreator = projectCreator;
    }

    public Date getDateOfCompletion() {
        return dateOfCompletion;
    }

    public void setDateOfCompletion(Date dateOfCompletion) {
        this.dateOfCompletion = dateOfCompletion;
    }

    public Double getReadinessDegree() {
        return readinessDegree;
    }

    public void setReadinessDegree(Double readinessDegree) {
        this.readinessDegree = readinessDegree;
    }

    public String getProjectCreatorName() {
        return projectCreatorName;
    }

    public void setProjectCreatorName(String projectCreatorName) {
        this.projectCreatorName = projectCreatorName;
    }

    public String getProjectCreatorSurname() {
        return projectCreatorSurname;
    }

    public void setProjectCreatorSurname(String projectCreatorSurname) {
        this.projectCreatorSurname = projectCreatorSurname;
    }
}
