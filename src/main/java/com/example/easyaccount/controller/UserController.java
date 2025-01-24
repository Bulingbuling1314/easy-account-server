package com.example.easyaccount.controller;

import com.example.easyaccount.common.ResultMap;
import com.example.easyaccount.entity.UserEntity;
import com.example.easyaccount.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultMap login(@RequestBody UserEntity data) {
        return userService.save(data);
    }
}
