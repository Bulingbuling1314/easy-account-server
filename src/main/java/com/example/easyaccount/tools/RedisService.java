package com.example.easyaccount.tools;

public interface RedisService {
    /**
     * 新增key
     * @param key
     * @param value
     */
    void set(String key, String value);

    /**
     * 通过key获取对应的value
     * @param key
     * @return
     */
    String get(String key);
}