package com.example.demo.service.impl;

import com.example.demo.models.entity.CategoryEntity;
import com.example.demo.models.entity.RoleEntity;
import com.example.demo.models.entity.enums.CategoryEntityNameEnum;
import com.example.demo.models.entity.enums.RoleEntityNameEnum;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RoleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class RoleServiceImplTest {


    private RoleServiceImpl serviceToTest;
    private RoleEntity roleEntity;
    private RoleEntityNameEnum roleEnum;

    @Mock
    private RoleRepository mockRoleRepository;

    @BeforeEach
    void inIt(){
        serviceToTest = new RoleServiceImpl(mockRoleRepository);
        roleEntity = new RoleEntity();
        roleEnum = RoleEntityNameEnum.MODERATOR;
        roleEntity.setRole(roleEnum);
    }

    @Test
    void initRoles(){
        serviceToTest.initializeRoles();
    }
    @Test
    void RoleFindByNameNOTFOUND(){
        RoleEntity expected = new RoleEntity();
        expected.setRole(RoleEntityNameEnum.ADMIN);
        mockRoleRepository.save(expected);
        RoleEntity actual = serviceToTest.findByRole(expected.getRole());
        Assertions.assertNull(actual);
    }

    @Test
    public void testInitRoleInDb() {
        Mockito.when(mockRoleRepository.findAll()).thenReturn(List.of(this.roleEntity));
        RoleService roleService = new RoleServiceImpl(this.mockRoleRepository);
        roleService.initializeRoles();
        Assertions.assertEquals(1, this.mockRoleRepository.findAll().size());


    }


}
