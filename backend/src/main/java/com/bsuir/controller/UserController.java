package com.bsuir.controller;


import com.bsuir.model.User;
import com.bsuir.model.paginationModel.ProjectPaginationModel;
import com.bsuir.model.paginationModel.UserPaginationModel;
import com.bsuir.model.viewModel.UserViewModel;
import com.bsuir.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.PUT)
    public UserViewModel save(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @RequestMapping(value = "/user-view-model",method = RequestMethod.POST)
    public UserViewModel saveUserViewModel(@RequestBody UserViewModel userViewModel) {
        userService.saveUserViewModel(userViewModel);
        return userService.getUserViewModelById(userViewModel.getIduser());
    }

    @RequestMapping(value = "/authorization", method = RequestMethod.POST)
    public UserViewModel authorization(@RequestBody User user) {
        return userService.authorization(user.getLogin(), user.getPassword());
    }

    @RequestMapping(method = RequestMethod.GET)
    public UserViewModel getUserViewModelById(@RequestParam("id")long id){
        return userService.getUserViewModelById(id);
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public List<UserViewModel> getAllUserViewModel() {
        return userService.getAllUserViewModel();
    }

    @RequestMapping(value = "/sort-parameter", method = RequestMethod.GET)
    public List<String> getSortParameter() {
        return userService.getSortParameter();
    }

    @RequestMapping(value = "/sort", method = RequestMethod.GET)
    public UserPaginationModel getSortedUser(
            @RequestParam("parameter") String parameter,
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("direction") boolean direction,
            @RequestParam("search") String search) {
        return userService.getSortedUser(parameter, page, size, direction);
    }
}
