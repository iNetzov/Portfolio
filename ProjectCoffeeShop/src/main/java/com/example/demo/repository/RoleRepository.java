package com.example.demo.repository;

import com.example.demo.models.entity.RoleEntity;
import com.example.demo.models.entity.enums.RoleEntityNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
    RoleEntity findByRole(RoleEntityNameEnum roleEntityNameEnum);
}
