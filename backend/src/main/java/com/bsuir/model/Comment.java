package com.bsuir.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Comment {
    private long idcomment;
    private Long task;
    private String comment;
    private Timestamp timeOfCreation;
    private long user;

    @Id
    @Column(name = "idcomment", nullable = false)
    public long getIdcomment() {
        return idcomment;
    }

    public void setIdcomment(long idcomment) {
        this.idcomment = idcomment;
    }

    @Basic
    @Column(name = "task", nullable = true)
    public Long getTask() {
        return task;
    }

    public void setTask(Long task) {
        this.task = task;
    }

    @Basic
    @Column(name = "comment", nullable = false, length = -1)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "time_of_creation", nullable = false)
    public Timestamp getTimeOfCreation() {
        return timeOfCreation;
    }

    public void setTimeOfCreation(Timestamp timeOfCreation) {
        this.timeOfCreation = timeOfCreation;
    }

    @Basic
    @Column(name = "user", nullable = false)
    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment1 = (Comment) o;
        return idcomment == comment1.idcomment &&
                user == comment1.user &&
                Objects.equals(task, comment1.task) &&
                Objects.equals(comment, comment1.comment) &&
                Objects.equals(timeOfCreation, comment1.timeOfCreation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idcomment, task, comment, timeOfCreation, user);
    }
}
