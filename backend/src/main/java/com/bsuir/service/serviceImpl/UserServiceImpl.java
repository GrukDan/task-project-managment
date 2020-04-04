package com.bsuir.service.serviceImpl;

import com.bsuir.model.User;
import com.bsuir.model.httpModel.UserViewModel;
import com.bsuir.repository.UserRepository;
import com.bsuir.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<UserViewModel> getAllUserViewModel() {
        List<User> users = userRepository.findAll();
        List<UserViewModel> userViewModels = new ArrayList<>();
        for(User user:users){
            userViewModels.add(new UserViewModel(user));
        }
        return userViewModels;
    }

    @Override
    public UserViewModel saveUser(User user) {
        UserViewModel userViewModel = null;
        if(userRepository.findByLoginAndPassword(user.getLogin(),user.getPassword())!=null){
            userViewModel = new UserViewModel(userRepository.save(user));
        }
        return userViewModel;
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
