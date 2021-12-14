package com.example.demo.repository;

import com.example.demo.models.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<StoreEntity,Long> {

    Optional<StoreEntity>findByName(String name);
}
