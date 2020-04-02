package com.bsuir.service.serviceImpl;

import com.bsuir.model.Role;
import com.bsuir.repository.RoleRepository;
import com.bsuir.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role getRoleById(long idrole) {
        return null;
    }

    @Override
    public List<Role> getAllRole() {
        return (List<Role>) roleRepository.findAll();
    }
}
