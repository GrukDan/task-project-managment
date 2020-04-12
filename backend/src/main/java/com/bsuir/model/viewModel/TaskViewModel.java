package com.bsuir.model.viewModel;

import com.bsuir.model.Task;

import java.sql.Date;
import java.sql.Timestamp;

public class TaskViewModel {
    private long idtask;
    private String taskName;
    private Date dateOfCreation;
    private Date dueDate;
    private String taskCode;

    private long status;
    private String statusName;

    private long priority;
    private String priorityName;

    private long taskCreator;
    private String taskCreatorName;
    private String taskCreatorSurname;

    private Long taskExecutor;
    private String taskExecutorName;
    private String taskExecutorSurname;

    private long project;
    private String projectName;

    private Timestamp updated;

    public TaskViewModel(){}

    public TaskViewModel(Task task){
        this.idtask = task.getIdtask();
        this.taskName = task.getTaskName();
        this.dateOfCreation = task.getDateOfCreation();
        this.dueDate = task.getDueDate();
        this.taskCode = task.getTaskName();
        this.status = task.getStatus();
        this.priority = task.getPriority();
        this.taskCreator = task.getTaskCreator();
        this.taskExecutor = task.getTaskExecutor();
        this.project = task.getProject();
        this.updated = task.getUpdated();
    }

    public TaskViewModel(long idtask, String taskName, Date dateOfCreation, Date dueDate, String taskCode, long status, String statusName, long priority, String priorityName, long taskCreator, String taskCreatorName, String taskCreatorSurname, Long taskExecutor, String taskExecutorName, String taskExecutorSurname, long project, String projectName, Timestamp updated) {
        this.idtask = idtask;
        this.taskName = taskName;
        this.dateOfCreation = dateOfCreation;
        this.dueDate = dueDate;
        this.taskCode = taskCode;
        this.status = status;
        this.statusName = statusName;
        this.priority = priority;
        this.priorityName = priorityName;
        this.taskCreator = taskCreator;
        this.taskCreatorName = taskCreatorName;
        this.taskCreatorSurname = taskCreatorSurname;
        this.taskExecutor = taskExecutor;
        this.taskExecutorName = taskExecutorName;
        this.taskExecutorSurname = taskExecutorSurname;
        this.project = project;
        this.projectName = projectName;
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "TaskViewModel{" +
                "idtask=" + idtask +
                ", taskName='" + taskName + '\'' +
                ", dateOfCreation=" + dateOfCreation +
                ", dueDate=" + dueDate +
                ", taskCode='" + taskCode + '\'' +
                ", status=" + status +
                ", statusName='" + statusName + '\'' +
                ", priority=" + priority +
                ", priorityName='" + priorityName + '\'' +
                ", taskCreator=" + taskCreator +
                ", taskCreatorName='" + taskCreatorName + '\'' +
                ", taskCreatorSurname='" + taskCreatorSurname + '\'' +
                ", taskExecutor=" + taskExecutor +
                ", taskExecutorName='" + taskExecutorName + '\'' +
                ", taskExecutorSurname='" + taskExecutorSurname + '\'' +
                ", project=" + project +
                ", projectName='" + projectName + '\'' +
                ", updated=" + updated +
                '}';
    }

    public long getIdtask() {
        return idtask;
    }

    public void setIdtask(long idtask) {
        this.idtask = idtask;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public long getPriority() {
        return priority;
    }

    public void setPriority(long priority) {
        this.priority = priority;
    }

    public String getPriorityName() {
        return priorityName;
    }

    public void setPriorityName(String priorityName) {
        this.priorityName = priorityName;
    }

    public long getTaskCreator() {
        return taskCreator;
    }

    public void setTaskCreator(long taskCreator) {
        this.taskCreator = taskCreator;
    }

    public String getTaskCreatorName() {
        return taskCreatorName;
    }

    public void setTaskCreatorName(String taskCreatorName) {
        this.taskCreatorName = taskCreatorName;
    }

    public String getTaskCreatorSurname() {
        return taskCreatorSurname;
    }

    public void setTaskCreatorSurname(String taskCreatorSurname) {
        this.taskCreatorSurname = taskCreatorSurname;
    }

    public Long getTaskExecutor() {
        return taskExecutor;
    }

    public void setTaskExecutor(Long taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public String getTaskExecutorName() {
        return taskExecutorName;
    }

    public void setTaskExecutorName(String taskExecutorName) {
        this.taskExecutorName = taskExecutorName;
    }

    public String getTaskExecutorSurname() {
        return taskExecutorSurname;
    }

    public void setTaskExecutorSurname(String taskExecutorSurname) {
        this.taskExecutorSurname = taskExecutorSurname;
    }

    public long getProject() {
        return project;
    }

    public void setProject(long project) {
        this.project = project;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }
}
