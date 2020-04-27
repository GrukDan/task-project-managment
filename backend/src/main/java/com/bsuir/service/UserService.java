package com.bsuir.service;

import com.bsuir.model.User;
import com.bsuir.model.paginationModel.UserPaginationModel;
import com.bsuir.model.viewModel.UserViewModel;

import java.util.List;

public interface UserService {

    List<User> getAllUser();

    void delete(long iduser);

    List<UserViewModel> getAllUserViewModel();

    UserViewModel saveUser(User user);

    UserViewModel saveUserViewModel(UserViewModel userViewModel);

    User getByLogin(String login);

    List<String> getSortParameter();

    UserPaginationModel getSortedUser(String parameter, int page, int size, boolean direction);

    UserViewModel getUserViewModelById(String encodingId);

    List<User> getUsersByAssignProject(long id);
}
