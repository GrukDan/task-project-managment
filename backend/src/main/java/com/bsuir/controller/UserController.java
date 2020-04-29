package com.bsuir.controller;

import com.bsuir.mail.MailService;
import com.bsuir.model.viewModel.UserForTask;
import com.bsuir.security.jwt.JwtResponse;
import com.bsuir.security.jwt.JwtToken;
import com.bsuir.security.jwt.JwtUserDetailsService;
import com.bsuir.model.User;
import com.bsuir.model.paginationModel.UserPaginationModel;
import com.bsuir.model.viewModel.UserViewModel;
import com.bsuir.service.RoleService;
import com.bsuir.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
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

    @Autowired
    private RoleService roleService;

    @Value("${aes.encryption.key}")
    private String key;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(method = RequestMethod.PUT)
    public UserViewModel save(@RequestBody User user) {
        UserViewModel userViewModel = userService.saveUser(user);
        //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userViewModel;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void delete(@RequestParam("idUser") long idUser) {
        userService.delete(idUser);
    }

    @RequestMapping(value = "/user-view-model", method = RequestMethod.POST)
    public UserViewModel saveUserViewModel(@RequestBody UserViewModel userViewModel) {
        userService.saveUserViewModel(userViewModel);
        return userService.getUserViewModelById(userViewModel.getIduser());
    }

//    @RequestMapping(value = "/sign-in", method = RequestMethod.POST)
//    public JwtResponse signIn(@RequestBody User req) {
//        try {
//            LOGGER.info("Try sign in user: " + req.getLogin());
//            authenticate(req.getLogin(), req.getPassword());
//        } catch (Exception e) {
//        }
//
//        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(req.getLogin());
//        final String token = jwtToken.generateToken(userDetails);
//        User user = userService.getByLogin(req.getLogin());
//        String idUser = String.valueOf(user.getIduser());
//        String roleName =  roleService.getRoleById(user.getRole()).getRole();
//        return new JwtResponse(
//                token,
//                user.getUserName(),
//                user.getUserSurname(),
//                idUser,
//                roleName);
//    }


    @RequestMapping(value = "/sign-in", method = RequestMethod.POST)
    public ResponseEntity signIn(@RequestBody User req) {
        try {
            LOGGER.info("Try sign in user: " + req.getLogin());
            authenticate(req.getLogin(), req.getPassword());
        } catch (Exception e) {
        }
        JwtResponse jwtResponse = createJwtResponse(req);
        if (jwtResponse != null) {
            return ResponseEntity.ok(jwtResponse);
        } else
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(jwtResponse);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            LOGGER.error("USER_DISABLED");
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            LOGGER.error("INVALID_CREDENTIALS");
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    private JwtResponse createJwtResponse(User req) {
        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(req.getLogin());
        final String token = jwtToken.generateToken(userDetails);
        User user = userService.getByLoginAndPassword(req.getLogin(), req.getPassword());
        JwtResponse jwtResponse = null;
        if (user != null) {
            String idUser = String.valueOf(user.getIduser());
            String roleName = roleService.getRoleById(user.getRole()).getRole();
            jwtResponse = new JwtResponse(
                    token,
                    user.getUserName(),
                    user.getUserSurname(),
                    idUser,
                    roleName);
        }
        return jwtResponse;
    }

    @RequestMapping(method = RequestMethod.GET)
    public UserViewModel getUserViewModelById(@RequestParam("id") String id) {
        return userService.getUserViewModelById(id);
    }

    @RequestMapping(value = "/project", method = RequestMethod.GET)
    public List<User> getUsersByAssignProject(@RequestParam("assignProject") long id) {
        LOGGER.info("Get user by assign project");
        return userService.getUsersByAssignProject(id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<UserViewModel> getAllUserViewModel() {
        return userService.getAllUserViewModel();
    }

    @RequestMapping(value = "/for-task", method = RequestMethod.GET)
    public List<UserForTask> getUserForTasksByAssignProject(@RequestParam("assignProject") long assignProject) {

        return userService.getUserForTaskByAssignProject(assignProject);
    }

    @RequestMapping(value = "/sort-parameter", method = RequestMethod.GET)
    public List<String> getSortParameter() {
        LOGGER.info("Get parameters fro users' sorting");
        return userService.getSortParameter();
    }

    @RequestMapping(value = "/sort", method = RequestMethod.GET)
    public UserPaginationModel getSortedUser(
            @RequestParam("parameter") String parameter,
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("direction") boolean direction,
            @RequestParam("search") String search) {
        LOGGER.info("Get sorted users with parameters: " +
                " page: " + String.valueOf(page) +
                " size: " + String.valueOf(size) +
                " direction: " + String.valueOf(direction));
        return userService.getSortedUser(parameter, page, size, direction);
    }
}
