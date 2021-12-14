package com.example.demo.repository;

import com.example.demo.models.entity.CategoryEntity;
import com.example.demo.models.entity.enums.CategoryEntityNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {

    Optional<CategoryEntity> findByName (CategoryEntityNameEnum nameEnum);
}
