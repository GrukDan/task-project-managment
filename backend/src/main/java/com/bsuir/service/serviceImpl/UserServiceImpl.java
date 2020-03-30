package com.bsuir.service.serviceImpl;

import com.bsuir.model.User;
import com.bsuir.model.httpModel.UserViewModel;
import com.bsuir.repository.UserRepository;
import com.bsuir.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUser() {
        return null;
    }

    @Override
    public boolean saveUser(User user) {
        return false;
    }

    @Override
    public boolean saveUser(UserViewModel userViewModel) {
        return false;
    }

    @Override
    public User authorization(String login, String password) {
        return null;
    }

    @Override
    public User getUserById(long iduser) {
        return null;
    }
}
