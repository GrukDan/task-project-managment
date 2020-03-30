package com.bsuir.service.serviceImpl;

import com.bsuir.model.Comment;
import com.bsuir.repository.CommentRepository;
import com.bsuir.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public boolean saveComment(Comment comment) {
        return false;
    }

    @Override
    public List<Comment> getAllCommentByTask(long task) {
        return null;
    }
}
