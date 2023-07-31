package com.example.roleBased.serviceImpl;

import com.example.roleBased.dao.RoleDao;
import com.example.roleBased.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl{

    @Autowired
    private RoleDao roleDao;

    //create new Role
    public Role createNewRole(Role role) {
        return roleDao.save(role);
    }
}
