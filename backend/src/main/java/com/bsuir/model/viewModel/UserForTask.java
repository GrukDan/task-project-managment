package com.bsuir.model.viewModel;

import com.bsuir.model.User;

public class UserForTask {

    private long iduser;
    private String userName;
    private String userSurname;
    private long assignProject;

    public UserForTask(){}

    public UserForTask(User user){
        this.iduser = user.getIduser();
        this.userName = user.getUserName();
        this.userSurname = user.getUserSurname();
        this.assignProject = user.getAssignProject();
    }

    @Override
    public String toString() {
        return "UserForTask{" +
                "iduser=" + iduser +
                ", userName='" + userName + '\'' +
                ", userSurname='" + userSurname + '\'' +
                ", assignProject=" + assignProject +
                ", assignProjectName='"  + '\'' +
                '}';
    }

    public long getIduser() {
        return iduser;
    }

    public void setIduser(long iduser) {
        this.iduser = iduser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public long getAssignProject() {
        return assignProject;
    }

    public void setAssignProject(long assignProject) {
        this.assignProject = assignProject;
    }

    public UserForTask(long iduser, String userName, String userSurname, long assignProject, String assignProjectName) {
        this.iduser = iduser;
        this.userName = userName;
        this.userSurname = userSurname;
        this.assignProject = assignProject;
    }
}
