package com.bsuir.service.serviceImpl;

import com.bsuir.model.Comment;
import com.bsuir.model.User;
import com.bsuir.model.viewModel.CommentViewModel;
import com.bsuir.repository.CommentRepository;
import com.bsuir.repository.UserRepository;
import com.bsuir.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public List<CommentViewModel> getAllCommentByTask(int size, long task) {
        Page<Comment> page= commentRepository
                .findAllByTask(
                        task,
                        PageRequest.of(0, size, Sort.by(Sort.Direction.DESC, "timeOfCreation")));
        List<Comment> comments = page.getContent();
        List<CommentViewModel> commentViewModels = new ArrayList<>();
        List<User> users = userRepository.findAll();
        comments.stream().forEach(comment -> {
            CommentViewModel commentViewModel = new CommentViewModel(comment);
            commentViewModel.setTotalComments(page.getTotalElements());
            commentViewModels.add(setUserNameSurname(commentViewModel, users));
        });
        return commentViewModels;
    }


    private CommentViewModel setUserNameSurname(CommentViewModel commentViewModel, List<User> users) {
        users.stream()
                .filter(user -> user.getIduser() == commentViewModel.getUser())
                .forEach(user -> {
                    commentViewModel.setUserName(user.getUserName());
                    commentViewModel.setUserSurname(user.getUserSurname());
                });
        return commentViewModel;
    }

}
