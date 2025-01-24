package com.example.easyaccount.repository;

import com.example.easyaccount.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    CategoryEntity findByName(String name);

    CategoryEntity findById(Long id);

    List<CategoryEntity> findByType(int type);
}
