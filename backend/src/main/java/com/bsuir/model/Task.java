package com.bsuir.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Task {
    private long idtask;
    private String taskName;
    private Date dateOfCreation;
    private Date dueDate;
    private String taskCode;
    private long status;
    private long priority;
    private long taskCreator;
    private Long taskExecutor;
    private long project;
    private String description;
    private Timestamp updated;

    public Task() {
    }

    public Task(long idtask, String taskName, Date dateOfCreation, Date dueDate, String taskCode, long status, long priority, long taskCreator, Long taskExecutor, long project, String description, Timestamp updated) {
        this.idtask = idtask;
        this.taskName = taskName;
        this.dateOfCreation = dateOfCreation;
        this.dueDate = dueDate;
        this.taskCode = taskCode;
        this.status = status;
        this.priority = priority;
        this.taskCreator = taskCreator;
        this.taskExecutor = taskExecutor;
        this.project = project;
        this.description = description;
        this.updated = updated;
    }

    @Id
    @Column(name = "idtask", nullable = false)
    public long getIdtask() {
        return idtask;
    }

    public void setIdtask(long idtask) {
        this.idtask = idtask;
    }

    @Basic
    @Column(name = "task_name", nullable = false, length = 45)
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Basic
    @Column(name = "date_of_creation", nullable = false)
    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    @Basic
    @Column(name = "due_date", nullable = false)
    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Basic
    @Column(name = "task_code", nullable = false, length = 45)
    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    @Basic
    @Column(name = "status", nullable = false)
    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    @Basic
    @Column(name = "priority", nullable = false)
    public long getPriority() {
        return priority;
    }

    public void setPriority(long priority) {
        this.priority = priority;
    }

    @Basic
    @Column(name = "task_creator", nullable = false)
    public long getTaskCreator() {
        return taskCreator;
    }

    public void setTaskCreator(long taskCreator) {
        this.taskCreator = taskCreator;
    }

    @Basic
    @Column(name = "task_executor", nullable = true)
    public Long getTaskExecutor() {
        return taskExecutor;
    }

    public void setTaskExecutor(Long taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    @Basic
    @Column(name = "project", nullable = false)
    public long getProject() {
        return project;
    }

    public void setProject(long project) {
        this.project = project;
    }

    @Basic
    @Column(name = "description", nullable = true, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "updated", nullable = true)
    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return idtask == task.idtask &&
                status == task.status &&
                priority == task.priority &&
                taskCreator == task.taskCreator &&
                project == task.project &&
                Objects.equals(taskName, task.taskName) &&
                Objects.equals(dateOfCreation, task.dateOfCreation) &&
                Objects.equals(dueDate, task.dueDate) &&
                Objects.equals(taskCode, task.taskCode) &&
                Objects.equals(taskExecutor, task.taskExecutor) &&
                Objects.equals(description, task.description) &&
                Objects.equals(updated, task.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idtask, taskName, dateOfCreation, dueDate, taskCode, status, priority, taskCreator, taskExecutor, project, description, updated);
    }
}
