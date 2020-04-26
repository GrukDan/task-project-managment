package com.bsuir.service;

import com.bsuir.model.Comment;
import com.bsuir.model.viewModel.CommentViewModel;

import java.util.List;

public interface CommentService {

     boolean saveComment(Comment comment);
     List<CommentViewModel> getAllCommentByTask(int size, long task);
}
