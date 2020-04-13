package com.bsuir.service.serviceImpl;

import com.bsuir.model.Project;
import com.bsuir.model.Role;
import com.bsuir.model.User;
import com.bsuir.model.paginationModel.UserPaginationModel;
import com.bsuir.model.viewModel.UserViewModel;
import com.bsuir.repository.ProjectRepository;
import com.bsuir.repository.RoleRepository;
import com.bsuir.repository.UserRepository;
import com.bsuir.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<User> getAllUser() {
        return null;
    }

    private String[] parameterForSorting = {"userName","userSurname","email","role"};

    @Override
    public List<UserViewModel> getAllUserViewModel() {
        List<User> users = userRepository.findAll();
        List<UserViewModel> userViewModels = new ArrayList<>();
        for (User user : users) {
            userViewModels.add(new UserViewModel(user));
        }
        return userViewModels;
    }

    @Override
    public UserViewModel saveUser(User user) {
        UserViewModel userViewModel = null;
        if (userRepository.findByLoginAndPassword(user.getLogin(), user.getPassword()) == null) {
            userViewModel = new UserViewModel(userRepository.save(user));
        }
        return userViewModel;
    }

    @Override
    public UserViewModel saveUserViewModel(UserViewModel userViewModel) {
        User user = userViewModel.buildUser();
        User user2 = userRepository.getOne(user.getIduser());
        user.setLogin(user2.getLogin());
        user.setPassword(user2.getPassword());
        userRepository.save(user);
        return userViewModel;
    }

    @Override
    public List<User> getUsersByAssignProject(long id) {
        return userRepository.findByAssignProject(id);
    }

    @Override
    public UserViewModel authorization(String login, String password) {
        return new UserViewModel(userRepository.findByLoginAndPassword(login, password));
    }

    @Override
    public User getUserById(long iduser) {
        return null;
    }

    @Override
    public List<String> getSortParameter() {
        return Arrays.asList(parameterForSorting);
    }

    @Override
    public UserPaginationModel getSortedUser(String parameter, int page, int size, boolean direction) {

        Page<User> userPage;
        if (direction)
            userPage = userRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, parameter)));
        else
            userPage = userRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, parameter)));

        List<User> userList = userPage.getContent();
        List<Role> roleList = (List<Role>) roleRepository.findAll();
        List<Long> assignProjects = new ArrayList<>();
        List<UserViewModel> userViewModelList = new ArrayList<>();
        for(User user:userList){
            if(user.getAssignProject()!=null && !assignProjects.contains(user.getAssignProject())){
                assignProjects.add(user.getAssignProject());
            }
            userViewModelList.add(new UserViewModel(user));
        }

        List<Project> projectList = projectRepository.findByIdprojectIn(assignProjects);
        for(UserViewModel userViewModel:userViewModelList){
            for(Project project : projectList) {
                if (userViewModel.getAssignProject()!=null && userViewModel.getAssignProject().equals(project.getIdproject())) {
                    userViewModel.setAssignProjectName(project.getProjectName());
                    break;
                }
            }
            for(Role role: roleList){
                if(userViewModel.getRole().equals(role.getIdrole())){
                    userViewModel.setRoleName(role.getRole());
                    break;
                }
            }
        }
        UserViewModel[] userViewModelArray = new UserViewModel[userViewModelList.size()];
        userViewModelList.toArray(userViewModelArray);

        return new UserPaginationModel(userPage.getTotalPages(),page,userViewModelArray);
    }

    @Override
    public UserViewModel getUserViewModelById(long id) {
        User user = userRepository.getOne(id);
        UserViewModel userViewModel = new UserViewModel(user);
        if(user.getAssignProject()!=null) {
            Project project = projectRepository.getOne(user.getAssignProject());
            userViewModel.setAssignProjectName(project.getProjectName());
        }
        Role role = roleRepository.findByIdrole(user.getRole());
        userViewModel.setRoleName(role.getRole());
        return userViewModel;
    }
}
