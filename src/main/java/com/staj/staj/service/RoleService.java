package com.staj.staj.service;

import com.staj.staj.model.Role;

public interface RoleService {
    Role findByName(String name);

    void addRole(Role role);
}
