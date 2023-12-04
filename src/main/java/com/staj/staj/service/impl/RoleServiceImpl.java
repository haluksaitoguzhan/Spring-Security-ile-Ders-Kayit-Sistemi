package com.staj.staj.service.impl;

import com.staj.staj.dao.RoleDao;
import com.staj.staj.model.Role;
import com.staj.staj.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Role findByName(String name) {
        Role role = roleDao.findRoleByName(name);
        return role;
    }

    @Override
    public void addRole(Role role) {
        roleDao.save(role);
    }


}
