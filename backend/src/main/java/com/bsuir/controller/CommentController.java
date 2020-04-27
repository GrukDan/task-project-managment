package com.bsuir.controller;

import com.bsuir.model.Comment;
import com.bsuir.model.viewModel.CommentViewModel;
import com.bsuir.service.CommentService;
import com.bsuir.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private CommentService commentService;
    
    @RequestMapping(value = "/all-comments", method = RequestMethod.GET )
    public List<CommentViewModel> getComment(
            @RequestParam("size") int size,
            @RequestParam("idTask") long idTask){
        return commentService.getAllCommentByTask(size,idTask);
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public void save(@RequestBody Comment comment){
        commentService.saveComment(comment);
    }
}
