package com.bsuir.model.viewModel;

import com.bsuir.model.User;

public class UserViewModel {
    private long iduser;

    private Long role;
    private String roleName;

    private String userName;
    private String userSurname;
    private String email;

    private Long assignProject;
    private String assignProjectName;

    public UserViewModel() {
    }

    public UserViewModel(User user) {
        this.iduser = user.getIduser();
        this.role = user.getRole();
        this.userName = user.getUserName();
        this.userSurname = user.getUserSurname();
        this.email = user.getEmail();
        this.assignProject = user.getAssignProject();
    }

    public UserViewModel(long iduser, Long role, String roleName, String userName, String userSurname, String email, Long assignProject, String assignProjectName) {
        this.iduser = iduser;
        this.role = role;
        this.roleName = roleName;
        this.userName = userName;
        this.userSurname = userSurname;
        this.email = email;
        this.assignProject = assignProject;
        this.assignProjectName = assignProjectName;
    }

    public User buildUser() {
        return new User(iduser, role, userName, userSurname, email, assignProject);
    }

    @Override
    public String toString() {
        return "UserViewModel{" +
                "iduser=" + iduser +
                ", role=" + role +
                ", roleName='" + roleName + '\'' +
                ", userName='" + userName + '\'' +
                ", userSurname='" + userSurname + '\'' +
                ", email='" + email + '\'' +
                ", assignProject=" + assignProject +
                ", assignProjectName='" + assignProjectName + '\'' +
                '}';
    }

    public long getIduser() {
        return iduser;
    }

    public void setIduser(long iduser) {
        this.iduser = iduser;
    }

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getAssignProject() {
        return assignProject;
    }

    public void setAssignProject(Long assignProject) {
        this.assignProject = assignProject;
    }

    public String getAssignProjectName() {
        return assignProjectName;
    }

    public void setAssignProjectName(String assignProjectName) {
        this.assignProjectName = assignProjectName;
    }
}
