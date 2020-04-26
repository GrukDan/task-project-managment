//package com.bsuir.security.jwt;
//
//import com.bsuir.model.Role;
//import com.bsuir.model.User;
//
//import com.bsuir.service.RoleService;
//import com.bsuir.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import static java.util.Collections.emptyList;
//
//@Service
//public class JwtUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserService userService;
//
//    @Override
//    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
//        User user = userService.getByLogin(login);
//        System.out.println("details server");
//        if (user == null)
//            throw new UsernameNotFoundException("User with login " + login + " not found");
//        System.out.println("User " + user.getLogin() + " log in!");
//        return new org.springframework.security.core.userdetails.User(
//                user.getLogin(),
//                user.getPassword(),
//                emptyList());
//    }
//}
