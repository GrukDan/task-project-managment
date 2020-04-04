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
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.PUT)
    public UserViewModel save(@RequestBody User user){
        return userService.saveUser(user);
    }

    @RequestMapping(value ="/authorization",method = RequestMethod.POST)
    public UserViewModel authorization(@RequestBody User user){
        userService.authorization(user.getLogin(),user.getPassword());
        return new UserViewModel();
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<UserViewModel> getAllUserViewModel(){
        return userService.getAllUserViewModel();
    }

    @RequestMapping(method = RequestMethod.POST)
    public boolean save(@RequestBody UserViewModel userViewModel){
        return userService.saveUser(userViewModel);
    }

}
