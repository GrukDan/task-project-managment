package com.bsuir.controller;


import com.bsuir.model.Role;
import com.bsuir.model.User;
import com.bsuir.model.httpModel.UserViewModel;
import com.bsuir.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    public boolean save(@RequestBody User user){
        return userService.saveUser(user);
    }

    @RequestMapping(value ="/user/authorization",method = RequestMethod.POST)
    public UserViewModel authorization(@RequestBody User user){
        userService.authorization(user.getLogin(),user.getPassword());
        return new UserViewModel();
    }

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public boolean save(@RequestBody UserViewModel userViewModel){
        return userService.saveUser(userViewModel);
    }

}
