package com.bsuir.model.viewModel;

import com.bsuir.model.Comment;

public class CommentViewModel extends Comment {
    private String userName;
    private String userSurname;
    private long totalComments;

    public CommentViewModel() {
    }

    public CommentViewModel(String userName, String userSurname) {
        this.userName = userName;
        this.userSurname = userSurname;
    }

    public CommentViewModel(Comment comment) {
        super(
                comment.getIdcomment(),
                comment.getTask(),
                comment.getComment(),
                comment.getTimeOfCreation(),
                comment.getUser()
        );
    }

    public long getTotalComments() {
        return totalComments;
    }

    public void setTotalComments(long totalComments) {
        this.totalComments = totalComments;
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
                ", totalComments=" + totalComments +
                '}';
    }
}
