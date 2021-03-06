package com.bsuir.model.viewModel;

import com.bsuir.model.User;

public class UserViewModel {
    private String iduser;

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
        this.iduser = String.valueOf(user.getIduser());
        this.role = user.getRole();
        this.userName = user.getUserName();
        this.userSurname = user.getUserSurname();
        this.email = user.getEmail();
        this.assignProject = user.getAssignProject();
    }

    public UserViewModel(long iduser, Long role, String roleName, String userName, String userSurname, String email, Long assignProject, String assignProjectName) {
        this.iduser = String.valueOf(iduser);
        this.role = role;
        this.roleName = roleName;
        this.userName = userName;
        this.userSurname = userSurname;
        this.email = email;
        this.assignProject = assignProject;
        this.assignProjectName = assignProjectName;
    }

    public User buildUser() {
        User user = new User();
        user.setIduser(Long.valueOf(iduser));
        user.setRole(role);
        user.setUserName(userName);
        user.setUserSurname(userSurname);
        user.setEmail(email);
        user.setAssignProject(assignProject);
        return user;
        //return new User(Long.valueOf(iduser), role, userName, userSurname, email, assignProject,null,null);
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

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
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
