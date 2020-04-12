package com.bsuir.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Project {
    private long idproject;
    private String projectCode;
    private String projectName;
    private long projectCreator;
    private Date dateOfCompletion;
    private Double readinessDegree;
    private String description;

    public Project() {
    }

    public Project(long idproject, String projectCode, String projectName, long projectCreator, Date dateOfCompletion, Double readinessDegree, String description) {
        this.idproject = idproject;
        this.projectCode = projectCode;
        this.projectName = projectName;
        this.projectCreator = projectCreator;
        this.dateOfCompletion = dateOfCompletion;
        this.readinessDegree = readinessDegree;
        this.description = description;
    }

    @Id
    @Column(name = "idproject", nullable = false)
    public long getIdproject() {
        return idproject;
    }

    public void setIdproject(long idproject) {
        this.idproject = idproject;
    }

    @Basic
    @Column(name = "project_code", nullable = false, length = 45)
    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    @Basic
    @Column(name = "project_name", nullable = false, length = 45)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Basic
    @Column(name = "project_creator", nullable = false)
    public long getProjectCreator() {
        return projectCreator;
    }

    public void setProjectCreator(long projectCreator) {
        this.projectCreator = projectCreator;
    }

    @Basic
    @Column(name = "date_of_completion", nullable = false)
    public Date getDateOfCompletion() {
        return dateOfCompletion;
    }

    public void setDateOfCompletion(Date dateOfCompletion) {
        this.dateOfCompletion = dateOfCompletion;
    }

    @Basic
    @Column(name = "readiness_degree", nullable = true, precision = 0)
    public Double getReadinessDegree() {
        return readinessDegree;
    }

    public void setReadinessDegree(Double readinessDegree) {
        this.readinessDegree = readinessDegree;
    }

    @Basic
    @Column(name = "description", nullable = true, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return idproject == project.idproject &&
                projectCreator == project.projectCreator &&
                Objects.equals(projectCode, project.projectCode) &&
                Objects.equals(projectName, project.projectName) &&
                Objects.equals(dateOfCompletion, project.dateOfCompletion) &&
                Objects.equals(readinessDegree, project.readinessDegree) &&
                Objects.equals(description, project.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idproject, projectCode, projectName, projectCreator, dateOfCompletion, readinessDegree, description);
    }
}
