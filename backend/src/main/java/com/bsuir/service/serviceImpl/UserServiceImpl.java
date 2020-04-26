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
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${aes.encryption.key}")
    private String key;

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
            userViewModels.add(
                    new UserViewModel(user)
            );
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
    public User getByLogin(String login) {
        return userRepository.findByLogin(login);
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
            setAssignProjectName(userViewModel,projectList);
            setRoleName(userViewModel,roleList);
        }

        UserViewModel[] userViewModelArray = new UserViewModel[userViewModelList.size()];
        userViewModelList.toArray(userViewModelArray);

        return new UserPaginationModel(userPage.getTotalPages(),page,userViewModelArray);
    }

    @Override
    public UserViewModel getUserViewModelById(String encodingId) {
        User user = userRepository.getOne(Long.parseLong(encodingId));
        UserViewModel userViewModel = new UserViewModel(user);
        setAssignProjectName(userViewModel);
        setRoleName(userViewModel);
        return userViewModel;
    }

    private UserViewModel setRoleName(UserViewModel userViewModel,List<Role> roles){
        if(userViewModel.getRole()!=null) {
            roles.stream()
                    .filter(role -> role.getIdrole() == userViewModel.getRole())
                    .forEach(role -> {
                        userViewModel.setRoleName(role.getRole());
                    });
        }
        return userViewModel;
    }

    private UserViewModel setAssignProjectName(UserViewModel userViewModel){
        if(userViewModel.getAssignProject()!=null) {
            Project project = projectRepository.getOne(userViewModel.getAssignProject());
            userViewModel.setAssignProjectName(project.getProjectName());
        }
        return userViewModel;
    }
    
    private UserViewModel setRoleName(UserViewModel userViewModel){
        if(userViewModel.getRole()!=null){
            Role role = roleRepository.findByIdrole(userViewModel.getRole());
            userViewModel.setRoleName(role.getRole());
        }
        return  userViewModel;
    }
    
    private UserViewModel setAssignProjectName(UserViewModel userViewModel,List<Project> projects){
        if(userViewModel.getAssignProject()!=null)
            projects.stream()
                    .filter(project -> project.getIdproject() == userViewModel.getAssignProject())
                    .forEach(project -> userViewModel.setAssignProjectName(project.getProjectName()));
        return userViewModel;
    }
    
}
