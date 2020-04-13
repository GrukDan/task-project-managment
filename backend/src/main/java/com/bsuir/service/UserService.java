package com.bsuir.service;

import com.bsuir.model.User;
import com.bsuir.model.paginationModel.UserPaginationModel;
import com.bsuir.model.viewModel.UserViewModel;

import java.util.List;

public interface UserService {

    List<User> getAllUser();

    List<UserViewModel> getAllUserViewModel();

    UserViewModel saveUser(User user);

    UserViewModel saveUserViewModel(UserViewModel userViewModel);

    UserViewModel authorization(String login, String password);

    User getUserById(long iduser);

    List<String> getSortParameter();

    UserPaginationModel getSortedUser(String parameter, int page, int size, boolean direction);

    UserViewModel getUserViewModelById(long id);

    List<User> getUsersByAssignProject(long id);
}
