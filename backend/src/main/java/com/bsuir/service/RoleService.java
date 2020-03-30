package com.bsuir.service;

import com.bsuir.model.Role;

import java.util.List;

public interface RoleService {

    Role getRoleById(long idrole);
    List<Role> getAllRole();
}
