package com.example.demo.service.impl;


import com.example.demo.models.entity.RoleEntity;
import com.example.demo.models.entity.UserEntity;
import com.example.demo.models.entity.enums.RoleEntityNameEnum;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class ApplicationUserServiceImplTest {

    private UserEntity testUser;
    private RoleEntity admin;
    private RoleEntity moderator;
    private RoleEntity user;

    private ApplicationUserServiceImpl serviceToTest;

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void init(){
        serviceToTest = new ApplicationUserServiceImpl(mockUserRepository);

        admin = new RoleEntity();
        admin.setRole(RoleEntityNameEnum.ADMIN);

        moderator = new RoleEntity();
        moderator.setRole(RoleEntityNameEnum.MODERATOR);

        user = new RoleEntity();
        user.setRole(RoleEntityNameEnum.USER);

        testUser = new UserEntity();
        testUser.setUsername("IvailoNetzov");
        testUser.setPassword("12345");
        testUser.setEmail("ivn9812@gmail.com");
        testUser.setFullName("Ivailo Netzov");
        testUser.setPicUrl("https://media.istockphoto.com/photos/eye-of-model-with-colorful-art-makeup-closeup-picture-id814423752?b=1&k=20&m=814423752&s=170667a&w=0&h=cXtr7JRtQAN60ezXjwbH_QqS4fPqg5LfPdMUnI-MeKo=");
        testUser.setRoles(Set.of(admin,moderator,user));
    }
    @Test
    void testUserNotFound(){
        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> serviceToTest.loadUserByUsername("invalid_user_email@not-exist.com")
        );
    }
    @Test
    void testUserFound(){

        Mockito.when(mockUserRepository.findByUsername(testUser.getUsername())).thenReturn(Optional.of(testUser));

        var actual = serviceToTest.loadUserByUsername(testUser.getUsername());

        String expectedRoles = "ROLE_ADMIN, ROLE_MODERATOR, ROLE_USER";
        String actualRoles = actual.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(", "));

        Assertions.assertEquals(actual.getUsername(),testUser.getUsername());
        Assertions.assertEquals(expectedRoles,actualRoles);
    }





}
