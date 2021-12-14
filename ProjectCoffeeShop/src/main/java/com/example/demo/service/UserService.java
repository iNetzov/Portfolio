package com.example.demo.service;

import com.example.demo.models.entity.UserEntity;
import com.example.demo.models.entity.enums.RoleEntityNameEnum;
import com.example.demo.models.service.UserServiceModel;
import com.example.demo.models.view.UserManageViewModel;

import java.util.List;
import java.util.Optional;

public interface UserService {


    void createUser(UserServiceModel user);

    UserEntity findByUsername(String username);

    UserEntity findByEmail(String email);

    void initializeUsers();
    Optional<UserEntity> updateUser(long userId, String fullName);

    List<UserManageViewModel> findAllUsers();


    void updateUserRole(UserEntity user, RoleEntityNameEnum newRole);

    List<UserEntity> findAllUsersEntity();
}
