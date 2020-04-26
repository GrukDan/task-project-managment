package com.bsuir.controller;

import com.bsuir.jwtSecurity.JwtResponse;
import com.bsuir.jwtSecurity.JwtToken;
import com.bsuir.jwtSecurity.JwtUserDetailsService;
import com.bsuir.model.User;
import com.bsuir.model.paginationModel.UserPaginationModel;
import com.bsuir.model.viewModel.UserViewModel;
import com.bsuir.service.RoleService;
import com.bsuir.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @RequestMapping(method = RequestMethod.PUT)
    public UserViewModel save(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @RequestMapping(value = "/user-view-model", method = RequestMethod.POST)
    public UserViewModel saveUserViewModel(@RequestBody UserViewModel userViewModel) {
        userService.saveUserViewModel(userViewModel);
        return userService.getUserViewModelById(userViewModel.getIduser());
    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public JwtResponse getUserByLoginAndPassword(@RequestBody User req) {

        try {
            authenticate(req.getLogin(), req.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }

        final UserDetails userDetails = jwtUserDetailsService

                .loadUserByUsername(req.getLogin());

        final String token = jwtToken.generateToken(userDetails);

        User user = userService.getByLogin(req.getLogin());
        return new JwtResponse(token,user.getUserName(),user.getUserSurname(),user.getIduser());
    }


    private void authenticate(String username, String password) throws Exception {

        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        } catch (DisabledException e) {

            throw new Exception("USER_DISABLED", e);

        } catch (BadCredentialsException e) {

            throw new Exception("INVALID_CREDENTIALS", e);

        }

    }

    @RequestMapping(method = RequestMethod.GET)
    public UserViewModel getUserViewModelById(@RequestParam("id") long id) {
        return userService.getUserViewModelById(id);
    }

    @RequestMapping(value = "/project", method = RequestMethod.GET)
    public List<User> getUsersByAssignProject(@RequestParam("assignProject") long id) {
        return userService.getUsersByAssignProject(id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
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
