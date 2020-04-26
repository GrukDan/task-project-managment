package com.bsuir.controller;

import com.bsuir.model.viewModel.CommentViewModel;
import com.bsuir.service.CommentService;
import com.bsuir.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private CommentService commentService;
    
    @RequestMapping(value = "/all-comments", method = RequestMethod.GET )
    public List<CommentViewModel> getComment(@RequestParam("idTask") long idTask ){
        return commentService.getAllCommentByTask(idTask);
    }
}
