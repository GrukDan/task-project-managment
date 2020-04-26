package com.bsuir.service.serviceImpl;

import com.bsuir.model.Comment;
import com.bsuir.model.viewModel.CommentViewModel;
import com.bsuir.repository.CommentRepository;
import com.bsuir.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<CommentViewModel> getAllCommentByTask(int size, long task) {
        List<Comment>  comments =  commentRepository
                .findAllByTask(
                        task,
                        PageRequest.of(0, size, Sort.by(Sort.Direction.DESC, "timeOfCreation"))).getContent();
        List<CommentViewModel> commentViewModels = new ArrayList<>();

        return null;
    }

}
