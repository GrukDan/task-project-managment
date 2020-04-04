package com.bsuir.controller;

import com.bsuir.model.Role;
import com.bsuir.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/role",method = RequestMethod.GET)
    public List<Role> getRole(){
        System.out.println("Hello from ROLE!");
        return roleService.getAllRole();
    }

}
