package com.bsuir.model.viewModel;

import com.bsuir.model.Comment;

public class CommentViewModel extends Comment {
    private String userName;
    private String userSurname;

    public CommentViewModel() {
    }

    public CommentViewModel(String userName, String userSurname) {
        this.userName = userName;
        this.userSurname = userSurname;
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

    @Override
    public String toString() {
        return "CommentViewModel{" +
                "userName='" + userName + '\'' +
                ", userSurname='" + userSurname + '\'' +
                '}' + super.toString();
    }
}
