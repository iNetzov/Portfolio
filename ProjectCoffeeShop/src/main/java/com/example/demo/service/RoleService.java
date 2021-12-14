package com.example.demo.service;

import com.example.demo.models.entity.RoleEntity;
import com.example.demo.models.entity.enums.RoleEntityNameEnum;

public interface RoleService {
    void initializeRoles();

    RoleEntity findByRole(RoleEntityNameEnum newRole);
}
