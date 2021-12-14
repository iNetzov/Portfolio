package com.example.demo.models.entity;

import com.example.demo.models.entity.enums.RoleEntityNameEnum;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class RoleEntity  {

    private Long id;
    private RoleEntityNameEnum role;

    public RoleEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Enumerated(EnumType.STRING)
    public RoleEntityNameEnum getRole() {
        return role;
    }

    public void setRole(RoleEntityNameEnum role) {
        this.role = role;
    }
}