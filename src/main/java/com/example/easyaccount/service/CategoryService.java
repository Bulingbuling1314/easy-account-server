package com.example.easyaccount.service;

import com.example.easyaccount.common.ResultMap;
import com.example.easyaccount.entity.CategoryEntity;
import com.example.easyaccount.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public ResultMap save(CategoryEntity category) {
        String name = category.getName();
        if(categoryRepository.findByName(name) != null) {
            return ResultMap.ok(categoryRepository.findByName(name));
        } else {
            return ResultMap.ok(categoryRepository.save(category));
        }
    }

    public ResultMap find(int type) {
        if(type == 0) {
            return ResultMap.ok(categoryRepository.findAll());
        }
        return ResultMap.ok(categoryRepository.findByType(type));
    }
}
