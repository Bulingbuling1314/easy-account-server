package com.example.easyaccount.service;

import cn.hutool.json.JSONUtil;
import com.example.easyaccount.common.ResultMap;
import com.example.easyaccount.entity.UserEntity;
import com.example.easyaccount.repository.UserRepository;
import com.example.easyaccount.tools.JWTUtil;
import com.example.easyaccount.tools.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private RedisService redisService;

    @Autowired
    private UserRepository userRepository;

    public ResultMap save(UserEntity user) {
        String openId = user.getOpenId();
        Map<String, Object> result = new HashMap<>();
        UserEntity userResult;

        if(userRepository.findByOpenId(openId) != null) {
            userResult = userRepository.findByOpenId(openId);
        } else {
            userResult = userRepository.save(user);
        }
        String token = JWTUtil.createToken(String.valueOf(userResult.getId()), 1000 * 60 * 30);
        result.put("token", token);
        // 把用户信息存进redis
        redisService.set("userInfo", JSONUtil.toJsonStr(userResult));
        return ResultMap.ok(result);
    }
}
