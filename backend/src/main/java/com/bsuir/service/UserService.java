package com.bsuir.service;

import com.bsuir.model.User;
import com.bsuir.model.httpModel.UserViewModel;

import java.util.List;

public interface UserService {

    List<User> getAllUser();
    boolean saveUser(User user);
    boolean saveUser(UserViewModel userViewModel);
    User authorization(String login,String password);
    User getUserById(long iduser);

}
