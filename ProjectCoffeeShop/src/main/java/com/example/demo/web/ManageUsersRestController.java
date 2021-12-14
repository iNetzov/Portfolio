package com.example.demo.web;

import com.example.demo.models.DTO.ManageUsersDTO;
import com.example.demo.models.entity.RoleEntity;
import com.example.demo.models.entity.UserEntity;
import com.example.demo.models.view.UserManageViewModel;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class ManageUsersRestController {
    private final UserService userService;

    public ManageUsersRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/manage-users")
    public ResponseEntity<List<ManageUsersDTO>> manageAllUsers() {
        List<UserEntity> allUsers = userService.findAllUsersEntity();
        List<ManageUsersDTO> DTOlist = new LinkedList<>();
        for (UserEntity user : allUsers) {
            ManageUsersDTO manage = new ManageUsersDTO();
            String username = user.getUsername();
            manage.setUsername(username);
            Set<RoleEntity> roles = user.getRoles();
            for (RoleEntity role : roles) {
                String name = role.getRole().name();
                manage.setRole(name);
            }
            DTOlist.add(manage);
        }

        return ResponseEntity.ok(DTOlist);


    }
}
