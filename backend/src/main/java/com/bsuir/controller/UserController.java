package com.bsuir.controller;

import com.bsuir.security.jwt.JwtResponse;
import com.bsuir.security.jwt.JwtToken;
import com.bsuir.security.jwt.JwtUserDetailsService;
import com.bsuir.model.User;
import com.bsuir.model.paginationModel.UserPaginationModel;
import com.bsuir.model.viewModel.UserViewModel;
import com.bsuir.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Value("${aes.encryption.key}")
    private String key;

    @RequestMapping(method = RequestMethod.PUT)
    public UserViewModel save(@RequestBody User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userService.saveUser(user);
    }

    @RequestMapping(value = "/user-view-model", method = RequestMethod.POST)
    public UserViewModel saveUserViewModel(@RequestBody UserViewModel userViewModel) {
        userService.saveUserViewModel(userViewModel);
        return userService.getUserViewModelById(userViewModel.getIduser());
    }

    @RequestMapping(value = "/sign-in", method = RequestMethod.POST)
    public JwtResponse signIn(@RequestBody User req)  {
        try {
            authenticate(req.getLogin(), req.getPassword());
        } catch (Exception e) {
        }

        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(req.getLogin());
        final String token = jwtToken.generateToken(userDetails);
        User user = userService.getByLogin(req.getLogin());
        String idUser = String.valueOf(user.getIduser());
        return new JwtResponse(
                token,
                user.getUserName(),
                user.getUserSurname(),
                idUser);
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
    public UserViewModel getUserViewModelById(@RequestParam("id") String id) {
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
