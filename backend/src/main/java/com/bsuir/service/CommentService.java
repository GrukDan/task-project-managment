package com.bsuir.service;

import com.bsuir.model.Comment;

import java.util.List;

public interface CommentService {

     boolean saveComment(Comment comment);
     List<Comment> getAllCommentByTask(long task);
}
