package com.example.easyaccount.controller;

import com.example.easyaccount.common.ResultMap;
import com.example.easyaccount.entity.CategoryEntity;
import com.example.easyaccount.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResultMap save(@RequestBody CategoryEntity data) {
        return categoryService.save(data);
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public ResultMap find(@RequestBody CategoryEntity data) {
        return categoryService.find(data.getType() != null ? data.getType() : 0);
    }
}
