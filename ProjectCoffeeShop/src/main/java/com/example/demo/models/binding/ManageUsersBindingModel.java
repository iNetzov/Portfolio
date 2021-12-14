package com.example.demo.models.binding;

import com.example.demo.models.entity.enums.RoleEntityNameEnum;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ManageUsersBindingModel {
    private String username;
    private RoleEntityNameEnum role;

    public ManageUsersBindingModel() {
    }

    @NotNull
    @Size(min = 1)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @NotNull
    public RoleEntityNameEnum getRole() {
        return role;
    }

    public void setRole(RoleEntityNameEnum role) {
        this.role = role;
    }
}
