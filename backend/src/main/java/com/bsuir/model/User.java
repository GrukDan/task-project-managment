package com.bsuir.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User {
    private long iduser;
    private Long role;
    private String userName;
    private String userSurname;
    private String email;
    private Long assignProject;
    private String login;
    private String password;



    @Id
    @Column(name = "iduser", nullable = false)
    public long getIduser() {
        return iduser;
    }

    public void setIduser(long iduser) {
        this.iduser = iduser;
    }

    @Basic
    @Column(name = "role", nullable = true)
    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }

    @Basic
    @Column(name = "user_name", nullable = false, length = 45)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "user_surname", nullable = false, length = 45)
    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 45)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "assign_project", nullable = true)
    public Long getAssignProject() {
        return assignProject;
    }

    public void setAssignProject(Long assignProject) {
        this.assignProject = assignProject;
    }

    @Basic
    @Column(name = "login", nullable = false, length = 100)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 100)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return iduser == user.iduser &&
                Objects.equals(role, user.role) &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(userSurname, user.userSurname) &&
                Objects.equals(email, user.email) &&
                Objects.equals(assignProject, user.assignProject) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iduser, role, userName, userSurname, email, assignProject, login, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "iduser=" + iduser +
                ", role=" + role +
                ", userName='" + userName + '\'' +
                ", userSurname='" + userSurname + '\'' +
                ", email='" + email + '\'' +
                ", assignProject=" + assignProject +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
