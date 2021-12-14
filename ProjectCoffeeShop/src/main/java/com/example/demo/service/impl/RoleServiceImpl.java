package com.example.demo.service.impl;

import com.example.demo.models.entity.RoleEntity;
import com.example.demo.models.entity.enums.RoleEntityNameEnum;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void initializeRoles() {
        if (roleRepository.count() == 0) {

            RoleEntity adminRole = new RoleEntity();
            adminRole.setRole(RoleEntityNameEnum.ADMIN);

            RoleEntity moderatorRole = new RoleEntity();
            moderatorRole.setRole(RoleEntityNameEnum.MODERATOR);

            RoleEntity userRole = new RoleEntity();
            userRole.setRole(RoleEntityNameEnum.USER);
            roleRepository.save(adminRole);
            roleRepository.save(moderatorRole);
            roleRepository.save(userRole);
        }
    }

    @Override
    public RoleEntity findByRole(RoleEntityNameEnum newRole) {
        return roleRepository.findByRole(newRole);
    }


}



